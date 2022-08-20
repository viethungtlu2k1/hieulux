import React, { Component } from "react";
import ConstantList from "../../appConfig";
import { KeyboardDatePicker, MuiPickersUtilsProvider } from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
// import Recaptcha from 'react-recaptcha';
import Autocomplete from '@material-ui/lab/Autocomplete'
import TextField from '@material-ui/core/TextField';
import { FLAGS, PHONE } from './Country'
import OTPDialog from "./OTPDialog";
import {
  Card,
  FormControlLabel,
  Grid,
  Button,
  Select,
  InputLabel,
  FormControl,
  MenuItem,
  RadioGroup,
  Radio,
  CircularProgress,
  withStyles,
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { connect } from "react-redux";
import { createUser, checkEmail, checkUsername } from "./SignUpService";
import { height, width } from "dom-helpers";
import is from "date-fns/esm/locale/is/index.js";

const styles = (theme) => ({
  wrapper: {
    position: "relative",
  },

  buttonProgress: {
    position: "absolute",
    top: "50%",
    left: "50%",
    marginTop: -12,
    marginLeft: -12,
  },
});

class SignUp extends Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.recaptchaLoaded = this.recaptchaLoaded.bind(this);
    this.verifyCallback = this.verifyCallback.bind(this);
    this.handleInputChange1 = this.handleInputChange1.bind(this);
    this.handleInputChange2 = this.handleInputChange2.bind(this);

    this.state = {
      isVerified: false,
      isChecked1: false,
      isChecked2: false,
      shouldOpenOtpDialog: false,
    }

  }
  handleInputChange1() {
    if (this.state.isChecked1 == false) {
      this.setState({ isChecked1: true })
    }
    else if (this.state.isChecked1 == true) {
      this.setState({ isChecked1: false })
    }
  }
  handleInputChange2() {
    if (this.state.isChecked2 == false) {
      this.setState({ isChecked2: true })
    }
    else if (this.state.isChecked2 == true) {
      this.setState({ isChecked2: false })
    }
  }
  recaptchaLoaded() {
    console.log('recaptcha loaded successfully')
  }
  handleSubmit() {

    if (this.state.isVerified == false && this.state.isChecked1 == false && this.state.isChecked2 == false) {
      alert('please enter the recaptcha and agree with our requirement')
    }
    else if (this.state.isVerified == false) {
      alert('please enter the recaptcha');
    }
    else if (this.state.isChecked1 == false || this.state.isChecked2 == false) {
      alert('please agree with our requirement');
    }
    else if (this.state.isVerified == true && this.state.isChecked1 == true && this.state.isChecked2 == true) {
      console.log('true');
    }
  }

  verifyCallback(response) {
    if (response) { this.setState({ isVerified: true }) }
  }

  state = {
    isVerified: "",
    isCheched1: "",
    isChecked2: "",
    shouldOpenOtpDialog: "",
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
    agreement: "",
    eqaRound: null,
    name: "",
    socialContact: "",
    isAddNew: false,
    roles: [],
    isNetwork: null,
    checkComboBox: 1,
    person: {},
    checkNullContact: false,
    socialContactFake: "",
    nameSocial: "Zalo: ",
    id: "",
    loading: false,
    showRegisterSuccessDialog: false,
    hostSite: "",
    phoneCode: "",
    countryCode: "",
    showTextBox: false,
    firstname: "",
    lastname: "",
    country: "",

  };
  listGender = [
    { value: 'Nam', name: 'Nam' },
    { value: 'Nu', name: 'Nữ' }
  ]

  handleChange = (event, source) => {
    event.persist();
    this.setState(
      {
        [event.target.name]: event.target.value,
        dupEmail: false,
        dupUsername: false

      },
      console.log(this.state.description)
    );
  };

  handleshowTextBox = (event) => {
    this.setState({
      showTextBox: event.target.value === "true",
    });
  };

  handleChangeCombo = (event, source) => {
    this.setState({ nameSocial: event.target.value + ": " });
  };

  handleFormSubmit = (event) => {
    this.setState({ loading: true });
    if (this.state.isVerified == false && this.state.isChecked1 == false && this.state.isChecked2 == false) {
      alert('please enter the recaptcha and agree with our requirement')
    }
    else if (this.state.isVerified == false) {
      alert('please enter the recaptcha');
    }
    else if (this.state.isChecked1 == false || this.state.isChecked2 == false) {
      alert('please agree with our requirement');
    }
    else if (this.state.isVerified == true && this.state.isChecked1 == true && this.state.isChecked2 == true) {
      console.log(' true');
      this.setState({ checkNetwork: false });
      checkEmail(this.state).then((result) => {
        if (result && result.data != null && result.data == false) {
          this.setState({ dupEmail: false });
          checkUsername(this.state).then((result) => {
            if (result && result.data != null && result.data == false) {
              this.setState({ dupUsername: false });
              this.setState({ dupUsername: false, dupEmail: false });
              createUser(this.state).then((user) => {
                this.setState({ loading: false });
                this.setState({ showRegisterSuccessDialog: true });
              });
            } else {
              this.setState({ dupUsername: true, loading: false });
            }
          });
        } else {
          this.setState({ dupEmail: true, loading: false });
        }
      });
    }



  };

  componentDidMount() {
    // this.setState({
    //   description: '\n Which categories do you interested in?: \n \n \n What suggestions do you have for us? :'
    // })
    // custom rule will have name 'isPasswordMatch'
    ValidatorForm.addValidationRule("isPasswordMatch", (value) => {
      if (value !== this.state.password) {
        return false;
      }
      return true;
    });
    this.setState({
      hostSite: window.location.origin + ConstantList.ROOT_PATH,
    });
  }
  handleDialogClose = () => {
    this.setState(
      {
        shouldOpenOtpDialog: false,
        data: [],
      },
    );
  };

  componentWillUnmount() {
    // remove rule when it is not needed
    ValidatorForm.removeValidationRule("isPasswordMatch");
  }

  toSignInPage = () => {
    // this.props.history.push(ConstantList.ROOT_PATH + "session/signin");
    window.open("http://gmail.com", "_self")
  };

  handleDialogClose = () => {
    this.setState(
      {
        showRegisterSuccessDialog: false,
      },
      function () {
        this.toSignInPage();
      }
    );
  };


  render() {
    const { t, i18n } = this.props
    let {
      name,
      confirmPassword,
      email,
      phone,
      firstname,
      lastname,
      school,
      fullname,
      phoneFake,
      gender,
      dob,
      description,
      password,
      username,
      isNetwork,
      website,
      trafficSource,
      comPany,
      socialContactFake,
      phoneCode,
      showRegisterSuccessDialog,
      showTextBox,
      country,
      countryCode,
      shouldOpenOtpDialog,
    } = this.state;
    let title;
    if (this.state.checkNetwork === true) {
      title = (
        <span style={{ color: "red" }}>
          {t("Validation.this_field_is_required")}
        </span>
      );
    } else {
      title = "";
    }


    let { classes } = this.props;
    let dupEmail;
    let dupUsername;
    if (this.state.dupEmail === true) {
      dupEmail = (
        <span className="w-100 mb-16 mr-16 ml-16" style={{ color: "red" }}>
          {" "}
          {t("Validation.check_email")}{" "}
        </span>
      );
    } else {
      dupEmail = "";
    }

    if (this.state.dupUsername === true) {
      dupUsername = (
        <span className="w-100 mb-16 mr-16 ml-16" style={{ color: "red" }}>
          {" "}
          {t("Validation.check_username")}{" "}
        </span>
      );
    } else {
      dupUsername = "";
    }
    return (

      <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
        <Grid
          item
          lg={12}
          md={12}
          sm={12}
          xs={12}
          spacing={2}
          style={{ backgroundImage: "url('https://natureorigin.vn/images/25-20210412040404.jpg')", height: '100%' }}
          className="signup flex flex-center w-100 h-100vh"
        >

          <Grid
            item
            lg={6}
            md={6}
            sm={6}
            xs={6}
            container
            style={{ backgroundColor: 'white' }}
            spacing={2}
          >
            {/* insert picture here */}
            <Grid item lg={12} md={12} sm={12} xs={12}>
              <div className="pt-36">
                {/* <h4 className="text-center">{t("sign_up.title_form")}</h4> */}
                <img src="/assets/images/unnamed.jpeg" style={{ display: 'block', width: '35%', height: '30%', marginLeft: 'auto', marginRight: 'auto' }} />
                {/* <img src={logo} width="100" height="50" /> */}


              </div>
            </Grid>
            <Grid
              item
              lg={12}
              md={12}
              sm={12}
              xs={12}
              container
              spacing={2}

            >

              <Grid item lg={6} md={6} sm={6} xs={12}>
                <TextValidator
                  variant="outlined"
                  className="w-100 mb-16 mr-16 ml-16"
                  label={
                    <span>
                      {t("Họ")}{" "}
                      <span style={{ color: "red" }}> * </span>
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="firstname"
                  value={firstname}
                  validators={["required"]}
                  errorMessages={t("Validation.this_field_is_required")}
                  size="small"

                />
              </Grid>
              <Grid item lg={6} md={6} sm={6} xs={12}>
                <TextValidator
                  variant="outlined"
                  className="w-100 mb-16 mr-16 ml-16"
                  label={
                    <span>
                      {t("Tên")}{" "}
                      <span style={{ color: "red" }}> * </span>
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="lastname"
                  value={lastname}
                  validators={["required"]}
                  errorMessages={t("Validation.this_field_is_required")}
                  size="small"

                />

              </Grid>

              {/* test */}
              {/* <Grid item lg={12} md={12} sm={12} xs={12}           
                  container           
                  spacing={0}>   
                  <Grid item lg={3} md={4} sm={4} xs={12}>   
                  <Autocomplete
                  className="w-200 mb-16 mr-0 ml-16"
                  options={PHONE}
                  defaultValue={()=>{
                    const defaultPhone = PHONE.filter(
                      (item) => item.name ===phone
                    );
                    return defaultPhone.length>0
                    ? defaultPhone[0]
                    :null
                  }}
                  style={{width:"100%"}}
                  onChange={(event,value)=>{
                    this.setState({
                      phoneCode:value !== null ? value.dial_code:"",
                    });
                  }}
                  getOptionLabel={(option)=> 
                    `${option.dial_code} ${option.name}`
                  }
                  getOptionSelected={(option,value)=>
                  option.value === value.value
                  }
                  renderInput={(params) => (
                    <TextValidator
                      {...params}
                      values={phoneCode}
                      label={
                        <span>
                          {t("sign_up.area_code")}{" "}
                          <span style ={{color:"red"}}>*</span>
                        </span>
                      }
                      validators={["required"]}
                      errorMessages={t("Validation.this_field_is_required")}
                    />
                  )}     
                  renderInput={(params) => (
                    <TextValidator
                      {...params}
                      variant="outlined"    
                      values={phoneCode}
                      label={
                        <span>
                          {t("sign_up.area_code")}{" "}
                          <span style ={{color:"red"}}>*</span>
                        </span>
                      }
                      validators={["required"]}
                      errorMessages={t("Validation.this_field_is_required")}
                      size="small"
                    />
                  )}                 
                />    
                </Grid>
                <Grid  item lg={9} md={8} sm={8} xs={12}>                          
                    <TextField     
                     className="w-100 mb-16 mr-0 ml-16"   
                      variant="outlined"
                      label={
                        <span>
                          {t("Tên")}{" "}
                          <span style={{ color: "red" }}> * </span>
                        </span>
                      }
                      onChange={this.handleChange}
                      type="text"
                      name="lastName"
                      value={lastname} 
                      validators={["required"]}
                      errorMessages={t("Validation.this_field_is_required")}
                      size="small"                      
                />          
                </Grid>    
                </Grid>                        */}
              {/* endtest */}
              <Grid item lg={12} md={12} sm={12} xs={12} container spacing={0}>
                <Grid item lg={4} md={4} sm={4} xs={12}>
                  <Autocomplete
                    options={PHONE}
                    defaultValue={() => {
                      const defaultPhone = PHONE.filter(
                        (item) => item.name === phone
                      );
                      return defaultPhone.length > 0
                        ? defaultPhone[0]
                        : null
                    }}
                    style={{ width: "100%" }}
                    onChange={(event, value) => {
                      this.setState({
                        phoneCode: value !== null ? value.dial_code : "",
                      });
                    }}
                    getOptionLabel={(option) =>
                      `${option.dial_code} ${option.name}`
                    }
                    getOptionSelected={(option, value) =>
                      option.value === value.value
                    }
                    renderInput={(params) => (
                      <TextValidator
                        className="w-100 mb-16 mr-16 ml-16"

                        {...params}
                        label={
                          <span>
                            {t("Mã vùng")}{" "}
                            <span style={{ color: "red" }}>*</span>
                          </span>
                        }
                        variant="outlined"
                        onChange={this.handleChange}
                        type="text"
                        value={phoneCode}
                        name="phoneCode"

                        validators={["required"]}
                        errorMessages={t("Validation.this_field_is_required")}
                        size="small"
                      />
                    )}
                  />
                </Grid>
                <Grid item lg={8} md={8} sm={8} xs={12}>
                  <TextValidator
                    variant="outlined"
                    className="w-100 mb-16 mr-16 ml-16"
                    label={
                      <span>
                        {t("sign_up.phone")}{" "}
                        <span style={{ color: "red" }}> * </span>
                      </span>
                    }
                    onChange={this.handleChange}
                    type="text"
                    name="phone"
                    value={phone}
                    validators={["required"]}
                    errorMessages={t("Validation.this_field_is_required")}
                    size="small"
                  />
                </Grid>
              </Grid>



              <Grid item lg={12} md={12} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16 mr-16 ml-16 "
                  variant="outlined"
                  label={
                    <span>
                      Email
                            <span style={{ color: "red" }}> * </span>
                    </span>
                  }
                  onChange={this.handleChange}
                  type="email"
                  name="email"
                  value={email}
                  validators={["required", "isEmail"]}
                  errorMessages={[
                    t("Validation.this_field_is_required"),
                    t("Validation.invalid_email"),
                  ]}
                  size="small"
                />
                {dupEmail}
              </Grid>

              <Grid item lg={12} md={12} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16 mr-16 ml-16"
                  label={
                    <span>
                      {t("Tên Đăng Nhập")}{" "}
                      <span style={{ color: "red" }}> * </span>
                    </span>
                  }
                  variant="outlined"
                  onChange={this.handleChange}
                  name="username"
                  type="text"
                  value={username} // fix
                  validators={[
                    "required",

                  ]}
                  errorMessages={[
                    t("Validation.this_field_is_required"),
                  ]}
                  size="small"
                />
                {dupUsername}
              </Grid>
              <Grid item lg={6} md={6} sm={6} xs={12}>
                <TextValidator
                  className="w-100 mb-16 mr-16 ml-16"
                  label={
                    <span>
                      {t("Mật khẩu")}{" "}
                      <span style={{ color: "red" }}> * </span>
                    </span>
                  }
                  variant="outlined"
                  onChange={this.handleChange}
                  name="password"
                  type="password"
                  value={password}
                  // validators={["required", "isPasswordMatch"]}
                  validators={["required"]}
                  errorMessages={[
                    t("Validation.this_field_is_required"),
                    t("Validation.invalid_password"),
                  ]}
                  size="small"
                />
              </Grid>

              <Grid item lg={6} md={6} sm={6} xs={12}>
                <TextValidator
                  className="w-100 mb-16 mr-16 ml-16"
                  label={
                    <span>
                      {t("Nhập lại mật khẩu")}{" "}
                      <span style={{ color: "red" }}> * </span>
                    </span>
                  }
                  variant="outlined"
                  onChange={this.handleChange}
                  name="confirmPassword"
                  type="password"
                  value={confirmPassword}//fix
                  validators={["required", "isPasswordMatch"]}
                  errorMessages={[
                    t("Validation.this_field_is_required"),
                    t("Validation.invalid_password"),
                  ]}
                  size="small"
                />
              </Grid>
              {/* <!--checkbox--> */}
              <Grid item lg={12} md={12} sm={12} xs={12}>
                <form style={{ marginLeft: "1rem" }}>
                  <label>
                    <input
                      name="checkbox1"
                      type="checkbox"

                      // checked
                      onChange={this.handleInputChange1} />
                        Đồng ý điều khoản thông tin đăng nhập
                    </label>
                </form>
              </Grid>

              <Grid item lg={12} md={12} sm={12} xs={12}>
                <form style={{ marginLeft: "1rem" }}>
                  <label>
                    <input
                      name="checkbox2"
                      type="checkbox"
                      // checked

                      onChange={this.handleInputChange2} />
                        Đồng ý đăng ký nhận thông tin từ Nature Origin
                    </label>
                </form>
              </Grid>
              <Grid item lg={12} md={12} sm={12} xs={12}>
                <form style={{ marginLeft: "1rem" }}>
                  <label>
                    Khi đăng kí tài khoản,bạn đồng ý với chính sách quyền riêng tư, các điều khoản của Nature Origin Việt Nam
                    </label>
                </form>
              </Grid>

              <Grid item lg={12} md={12} sm={12} xs={12}  >
                <div style={{ marginLeft: "1em" }}>
                  {/* <Recaptcha                 
                  sitekey="6Ld5c74aAAAAAOzBYMJv6JQtDSylXN0kVzg5vPnH"
                  render="explicit"
                  onloadCallback={this.recaptchaLoaded}
                  verifyCallback={this.verifyCallback}
                /> */}
                </div>
              </Grid>
              {/* submit button */}
              <Grid item lg={12} md={12} sm={12} xs={12}>
                <Button
                  //  onClick={this.handleFormSubmit}
                  className="capitalize"
                  variant="contained"
                  color="primary"
                  type="submit"
                  // disabled={this.state.loading}
                  fullWidth
                >
                  {t("sign_up.title")}
                </Button>
                {this.state.loading && <CircularProgress size={24} />}
              </Grid>
              <Grid item lg={12} md={12} sm={12} xs={12}>
                <div style={{ textAlign: "center" }}>Bạn đã có tài khoản ?
                <a
                    style={{ color: "blue" }}
                    // className="capitalize"
                    disabled={this.state.loading}
                    onClick={() =>
                      this.props.history.push(
                        ConstantList.ROOT_PATH + "session/signin"
                      )
                    }>Đăng nhập
                </a>
                </div>
                <Grid item lg={12} md={12} sm={12} xs={12}>

                  <Autocomplete
                    className="w-20 mb-16 mr-16 ml-16"
                    options={FLAGS}
                    defaultValue={() => {
                      const defaultCountry = FLAGS.filter(
                        (item) => item.name === country
                      );
                      return defaultCountry.length > 0
                        ? defaultCountry[0]
                        : null
                    }}
                    style={{ width: "20%" }}
                    onChange={(event, value) => {
                      this.setState({
                        countryCode: value !== null ? value.name : "",
                      });
                    }}
                    getOptionLabel={(option) =>
                      `${option.name}`
                    }
                    getOptionSelected={(option, value) =>
                      option.value === value.value
                    }
                    renderInput={(params) => (
                      <TextValidator
                        {...params}
                        values={countryCode}
                        label={
                          <span>
                            {t("Ngôn ngữ")}{" "}

                          </span>
                        }

                      />
                    )}
                  />
                </Grid>

                <Grid item xs={12}>
                  <div>
                    {this.state.shouldOpenEditorDialog && (
                      <OTPDialog
                        t={t}
                        i18n={i18n}
                        handleClose={this.handleDialogClose}
                        open={this.state.shouldOpenEditorDialog}
                        handleOKEditClose={this.handleOKEditClose}
                        item={this.state.item}
                      />
                    )}
                  </div>
                </Grid>

                {/* <Button
                    className="capitalize"
                    disabled={this.state.loading}
                    onClick={() =>
                      this.props.history.push(
                        ConstantList.ROOT_PATH + "session/signin"
                      )
                    }
                  >
                    {t("sign_in.title")}
                  </Button> */}
              </Grid>










              {/* <Grid item lg={6} md={6} sm={6} xs={12}>
                      <TextValidator
                        variant="outlined"
                        className="w-100 mb-16 mr-16 ml-16"
                        label={<span>{t("sign_up.website")}</span>}
                        onChange={this.handleChange}
                        type="text"
                        name="website"
                        validators={[
                          "matchRegexp:[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,256}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)",
                        ]}
                        errorMessages={[
                          t("Validation.invalid_url"),
                        ]}
                        value={website}
                      />
                    </Grid> */}







              {/* <Grid item lg={6} md={6} sm={6} xs={12}>
                {showTextBox && (
                  <TextValidator
                    variant="outlined"
                    className="w-100 mb-16 mr-16 ml-16"
                    label={
                      <span>
                        {t("sign_up.company")}{" "}
                        <span style={{ color: "red" }}> * </span>
                      </span>
                    }
                    onChange={this.handleChange}
                    type="text"
                    name="comPany"
                    value={comPany}
                    validators={["required"]}
                    errorMessages={t("Validation.this_field_is_required")}
                  />
                )}
              </Grid> */}
            </Grid>
            {/* <Grid item lg={6} md={6} sm={6} xs={12}>
              <div className="pl-36 pr-36 pb-36 h-100">
                <div className="flex flex-middle">
                  <div className={classes.wrapper}>
                    <Button onClick={this.handleSubcribe}
                      className="capitalize"
                      variant="contained"
                      color="primary"
                      type="submit"
                      disabled={this.state.loading}
                    >
                      {t("sign_up.title")}
                    </Button>

                    {this.state.loading && <CircularProgress size={24} />}
                  </div>

                  <span className="ml-16 mr-8">{t("sign_up.or")}</span>
                  <Button
                    className="capitalize"
                    disabled={this.state.loading}
                    onClick={() =>
                      this.props.history.push(
                        ConstantList.ROOT_PATH + "session/signin"
                      )
                    }
                  >
                    {t("sign_in.title")}
                  </Button>
                </div>
              </div>
            </Grid> */}
          </Grid>
        </Grid>

      </ValidatorForm>

    );
  }
}

const mapStateToProps = (state) => ({
  // setUser: PropTypes.func.isRequired
});

export default withStyles(styles, { withTheme: true })(
  connect(mapStateToProps, {})(SignUp)
);
