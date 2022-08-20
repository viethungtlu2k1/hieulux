import React, { Component } from "react";
import {
  Card,
  Checkbox,
  FormControlLabel,
  Grid,
  Button,
  withStyles,
  CircularProgress
} from "@material-ui/core";
import { TextValidator, ValidatorForm } from "react-material-ui-form-validator";
import { connect } from "react-redux";
import { PropTypes } from "prop-types";
import { withRouter } from "react-router-dom";
import { loginWithEmailAndPassword, loginWithEmailAndPasswordFB  } from "../../redux/actions/LoginActions";
import { Helmet } from "react-helmet";
import { TextField } from "material-ui";
import { FLAGS, PHONE } from './Country';
import Autocomplete from '@material-ui/lab/Autocomplete';
import env from './env';
import axios from 'axios';
import { accountService } from './AccountFacebookService';
import ConstantList from "../../appConfig";

const styles = theme => ({
  wrapper: {
    position: "relative"
  },

  buttonProgress: {
    position: "absolute",
    top: "50%",
    left: "50%",
    marginTop: -12,
    marginLeft: -12
  }
});

class SignIn extends Component {
  state = {
    email: "",
    password: "",
    agreement: "",
    countryCode: "",
    country: ""
  };

  handleChange = event => {
    event.persist();
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  // componentDidMount() {
  //   let { enableDisableAllLoading } = this.props;
  //   enableDisableAllLoading(false);
  // }

  componentWillMount() {
    this.googleSDK();
    this.initFacebookSdk()
  }

  handleFormSubmit = event => {
    event.preventDefault();
    this.setState({
      isLoading: true
    });
    let that = this;
    this.props.loginWithEmailAndPassword({ ...this.state }).then(result => {
      that.setState({
        isLoading: false
      })
    });
  };

  prepareLoginButton = () => {

    console.log(this.refs.googleLoginBtn);

    this.auth2.attachClickHandler(this.refs.googleLoginBtn, {},
      (googleUser) => {

        let profile = googleUser.getBasicProfile();
        console.log('Token || ' + googleUser.getAuthResponse().id_token);
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());


        let objectLogin = {
          email: profile.getId(),
          password: profile.getId()
        }
        this.props.loginWithEmailAndPassword({ ...objectLogin }).then(result => {

          if (result.type == env.LOGIN_ERROR_FAIL) {
            let register = {};
            register.displayName = profile.getName();
            register.username = profile.getId();
            register.email = profile.getEmail();
            register.password = profile.getId();
            // registerFaceBook(register).then((result) => {
            //   if (result != null && result.data != null && result.data != '') {
            //     this.props.history.push({
            //       pathname: ConstantList.ROOT_PATH + "session/signup",
            //       objectLogin: objectLogin
            //     });
            //   }
            //   else {
            //     alert('Có lỗi xảy ra khi đăng ký.');
            //   }
            // });
          }
          this.setState({
            isLoading: false
          })
        });

      }, (error) => {
        alert(JSON.stringify(error, undefined, 2));
      });

  }

  googleSDK = () => {
    window['googleSDKLoaded'] = () => {
      window['gapi'].load('auth2', () => {
        this.auth2 = window['gapi'].auth2.init({
          client_id: '405747889169-mniaunjubaames5mir0o9edmj7gf66df.apps.googleusercontent.com',
          cookiepolicy: 'single_host_origin',
          scope: 'profile email'
        });
        this.prepareLoginButton();
      });
    }

    (function (d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) { return; }
      js = d.createElement(s); js.id = id;
      js.src = env.SDKL_GOOGLE;
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'google-jssdk'));

  }


  newAccountId() {
    const accountsKey = 'react-facebook-login-accounts';
    let accounts = JSON.parse(localStorage.getItem(accountsKey)) || [];
    return accounts.length ? Math.max(...accounts.map(x => x.id)) + 1 : 1;
  }
  async loginFB() {
    // redirect to home if already logged in
    const accountsKey = 'react-facebook-login-accounts';
    let accounts = JSON.parse(localStorage.getItem(accountsKey)) || [];
    const { authResponse } = await new Promise(window.FB.login);
    if (!authResponse) return;
    axios.get(env.ACCESS_TOKEN + `${authResponse.accessToken}`)
      .then(response => {
        const { data } = response;
        let account = accounts.find(x => x.facebookId === data.id);
        if (!account) {
          // create new account if first time logging in
          account = {
            id: this.newAccountId(),
            facebookId: data.id,
            name: data.name,
            extraInfo: `This is some extra info about ${data.name} that is saved in the API`
          }
          accounts.push(account);
          localStorage.setItem(accountsKey, JSON.stringify(accounts));
        }
        let accessToken = account;
        let objectLogin = {
          email: accessToken.facebookId,
          password: accessToken.facebookId
        }
        this.props.loginWithEmailAndPasswordFB({ ...objectLogin, returnUrl: this.props.location.state?.returnUrl }).then(result => {

          if (result.type == env.LOGIN_ERROR_FAIL) {
            this.props.history.push({
              pathname: ConstantList.ROOT_PATH + "session/signup",
              accounts: accounts
            });

            // let register = {};
            // register.displayName = accessToken.name;
            // register.username = accessToken.facebookId;
            // register.email = accessToken.facebookId + '@gmail.com';
            // register.password = accessToken.facebookId;
            // registerFaceBook(register).then((result) => {
            //   if (result != null && result.data != null && result.data != '') {
            //     this.props.loginWithEmailAndPasswordFB({ ...objectLogin, returnUrl: this.props.location.state?.returnUrl }).then(data => {

            //     })
            //   }
            //   else {
            //     alert('Có lỗi xảy ra khi đăng ký.');
            //   }
            // });
          }
          this.setState({
            isLoading: false
          })
        });
      });
  }



  initFacebookSdk() {
    return new Promise(resolve => {
      // wait for facebook sdk to initialize before starting the react app
      window.fbAsyncInit = function () {
        window.FB.init({
          appId: env.REACT_APP_FACEBOOK_APP_ID,
          cookie: true,
          xfbml: true,
          version: 'v8.0'
        });

        // auto authenticate with the api if already logged in with facebook
        window.FB.getLoginStatus(({ authResponse }) => {
          if (authResponse) {
            accountService.apiAuthenticate(authResponse.accessToken).then(resolve);
          } else {
            resolve();
          }
        });
      };

      // load facebook sdk script
      (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) { return; }
        js = d.createElement(s); js.id = id;
        js.src = env.SDK_FACEBOOK;
        fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'facebook-jssdk'));
    });


  }

  render() {
    const { t, i18n } = this.props;
    let { email, password, countryCode, country } = this.state;
    let { classes } = this.props;
    return (
      <div className="signup flex flex-center w-100vw h-100vh">
        <div className="w-100 border-0">
          <Helmet>
            <title>
              {t("Dashboard.login")} | {t("web_site")}
            </title>
          </Helmet>
          <Card className="signup-card position-relative y-center">
            <Grid container>
              <Grid item lg={6} md={6} sm={6} xs={12}>
                <div className="flex flex-center h-100 w-100">
                  <img src="/assets/images/logos/campaign.png" style={{ backgroundColor: "#ffa900", width: "100vw", height: "100vh" }} alt="" />
                </div>
              </Grid>
              <Grid item lg={6} md={6} sm={6} xs={12}>
                <div className="p-36 h-100 position-relative">
                  {/* <Grid item lg={12} md={12} sm={12} xs={12}>

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
                  </Grid> */}
                  <Grid item lg={12} md={12} sm={12} xs={12}>
                    <form style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center" }}>
                      <div>
                        <label style={{ fontSize: "20px" }}>ĐĂNG NHẬP</label>
                      </div>
                      <div className="m-20">
                        <label>để truy cập <span style={{ color: "#ffa900" }}>offerpro fake</span></label>
                      </div>
                    </form>
                  </Grid>

                  <Grid >
                    <div className="flex flex-center flex-middle mb-40">
                      <img src="https://natureorigin.vn/images/asset-2offer-20210408021711.png" alt="" />
                    </div>
                  </Grid>
                  <Grid style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center" }}>
                    <ValidatorForm style={{ width: "40%" }} ref="form" onSubmit={this.handleFormSubmit}>
                      <TextValidator
                        className="mb-24 w-100"
                        variant="outlined"
                        label={t("username")}
                        onChange={this.handleChange}
                        type="text"
                        name="email"
                        value={email.trim()}
                        validators={["required"]}
                        errorMessages={[t("general.errorMessages_required")]}
                      />
                      <TextValidator
                        className="mb-16 w-100"
                        label={t("password")}
                        variant="outlined"
                        onChange={this.handleChange}
                        name="password"
                        type="password"
                        value={password.trim()}
                        validators={["required"]}
                        errorMessages={[t("general.errorMessages_required")]}
                      />
                      <div className="flex flex-end mb-10">
                        <Button
                          style={{ fontSize: "12px" }}
                        >
                          {t("forgot_password")}
                        </Button>
                      </div>

                      <div className="flex flex-center mb-10">
                        <Button
                          className="text-primary"
                          onClick={() =>
                            this.props.history.push("signup")
                          }
                          style={{ color: "#36a420" }}
                        >
                          {t("registry")}
                        </Button>
                      </div>

                      <div className={classes.wrapper} style={{ width: "100%", display: "flex", justifyContent: "center", alignItems: "center" }}>
                        <Button
                          variant="contained"

                          disabled={this.props.login.loading}
                          type="submit"
                          style={{ width: "60%", borderRadius: "15px", color: "white", backgroundColor: "#ffa900" }}
                        >
                          {t("sign_in.title")}
                        </Button>
                        {this.props.login.loading && (
                          <CircularProgress
                            size={24}
                            className={classes.buttonProgress}
                          />
                        )}
                      </div>

                      <div className="flex flex-center mt-20 mb-10">
                        <span>{t("or_with")}</span>
                      </div>
                    </ValidatorForm>
                  </Grid>
                  <Grid style={{ display: "flex", alignItems: "center", justifyContent: "center", marginBottom: "30px" }}>
                    <div style={{ width: "40%" }}>
                      <button
                        className="btn btn-light btn-lg btn-space loginBtn loginBtn--facebook w-100"
                        onClick={() => { this.loginFB() }}
                      >
                        {t("sign_in.continue_facebook")}
                      </button>
                      <button className="btn btn-light btn-lg btn-space  loginBtn loginBtn--google w-100" ref="googleLoginBtn"  >
                        {t("sign_in.continue_google")}
                      </button>
                    </div>
                  </Grid>

                  <Grid className="flex flex-center" style={{ fontSize: "12px" }}>
                    <Grid item lg={4} md={4} sm={4} xs={12}>
                      <div>
                        <span>Power by Nuture Origin</span>
                      </div>
                    </Grid>

                    <Grid item lg={8} md={8} sm={8} xs={12}>
                      <div style={{ color: "#ffa900", display: "flex", justifyContent: "space-around" }}>
                        <span>Trợ giúp</span>
                        <span>Chính sách bảo mật</span>
                        <span>Điều khoản sử dụng</span>
                      </div>
                    </Grid>
                  </Grid>
                </div>
              </Grid>
            </Grid>
          </Card>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  loginWithEmailAndPassword: PropTypes.func.isRequired,
  loginWithEmailAndPasswordFB: PropTypes.func.isRequired,
  login: state.login
});
export default withStyles(styles, { withTheme: true })(
  withRouter(connect(mapStateToProps, { loginWithEmailAndPassword, loginWithEmailAndPasswordFB })(SignIn))
);
