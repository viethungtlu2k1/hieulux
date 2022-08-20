import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  DialogActions,
  FormControl,
  Paper,
  DialogTitle,
  DialogContent,
  TextField
} from "@material-ui/core";
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
// import Paper from '@material-ui/core/Paper'
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import Draggable from "react-draggable";
import NotificationPopup from "../Component/NotificationPopup/NotificationPopup";
import {
  saveItem,
  addItem,
  updateItem,
  checkCode,
  searchByPage
} from "./CampaignService";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { searchByPage as getCampaignType } from "../CampaignType/CampaignTypeService";
import { searchByPage as getProduct } from "../Product/ProductService";
import Autocomplete from "@material-ui/lab/Autocomplete";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
import moment from "moment";

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

function PaperComponent(props) {
  return (
    <Draggable
      handle="#draggable-dialog-title"
      cancel={'[class*="MuiDialogContent-root"]'}
    >
      <Paper {...props} />
    </Draggable>
  );
}

var key = "";

class CampaignDialog extends Component {
  state = {
    id: "",
    name: "",
    code: "",
    campaignTypes: [],
    campaignType: {},
    startDate: "",
    endDate: "",
    epc: "",
    cvr: "",
    commission: "",
    approved_rate_30days: "",
    type: "",
    products: [],
    product: {},
    shouldOpenNotificationPopup: false,
    Notification: "",
    campaignTypeTreeView: {}
  };

  constructor(props) {
    super(props);
    this.handleTextChange = this.handleTextChange.bind(this);

  }

  handleTextChange(event) {
    key = event.target.value
    // this.setState({ keyword: event.target.value },()=>{
    //   console.log(this.state.keyword)
    // });
  }

  handleDialogClose = () => {
    this.setState({ shouldOpenNotificationPopup: false });
  };

  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  handleDateChange = (date, name) => {
    this.setState({
      [name]: date

    });
  }

  handleFormSubmit = () => {
    let { id } = this.state;
    let { code } = this.state;
    var { t } = this.props;
    var checkTime = moment(this.state.startDate).isBefore(this.state.endDate);  
    var checkTime2 = new Date() <= (this.state.endDate);
    let object = {
      startDate: this.state.fromDate,
      endDate: this.state.toDate
    }
    checkCode(id, code).then((result) => {
      //Nếu trả về true là code đã được sử dụng
      if (result.data) {
        toast.warning(t("general.dupli_code"));
        // alert("Code đã được sử dụng");
      } else {
        //Nếu trả về false là code chưa sử dụng có thể dùng
        if(id.length > 0) {
          if (checkTime && checkTime2) {
            updateItem({
              ...this.state,
            }).then(() => {
              toast.success(t("general.updateSuccess"));
              this.props.handleOKEditClose();
            });
          } else {
            if (!checkTime) toast.error("Thời gian bắt đầu phải trước thời gian kết thúc");
            if (!checkTime2) toast.error("Thời gian kết thúc phải sau thời gian hiện tại");
          }
        } else {
          saveItem({
            ...this.state,
          }).then(() => {
            toast.success(t("general.addSuccess"));
            this.props.handleOKEditClose();
          });
        }
      }
    });
  };

  componentWillMount() {
    //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
    let { open, handleClose, item, product } = this.props;
    console.log(product);
    this.setState({ ...item });
    this.setState({ product });

    let searchObject = {}
    searchObject.keyword = this.state.keyword
    searchObject.pageIndex = 1
    searchObject.pageSize = 10000
    getCampaignType(searchObject).then(({ data }) => {
      this.setState({ campaignTypes: [...data.content] }, () => {
      })
    })
    getProduct(searchObject).then(({ data }) => {
      this.setState({ products: [...data.content] }, () => {
      })
    })
  }

  render() {
    let {
      id,
      name,
      code,
      campaignType,
      shouldOpenNotificationPopup,
      campaignTypes,
      campaignTypeId,
      startDate,
      endDate,
      epc,
      cvr,
      commission,
      approved_rate_30days,
      product,
      products
    } = this.state;
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;
    return (
      <Dialog
        open={open}
        PaperComponent={PaperComponent}
        maxWidth="sm"
        fullWidth
      >
        <DialogTitle
          style={{ cursor: "move", paddingBottom: "0px" }}
          id="draggable-dialog-title"
        >
          <h4 className="">{id ? t("general.update") : t("general.addNew")}</h4>
        </DialogTitle>

        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
          <DialogContent>
            <Grid className="" container spacing={2}>
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("general.name")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="name"
                  value={name}
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("general.code")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="code"
                  value={code}
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={12} xs={12}>
                <Autocomplete
                  id="combo-box"
                  options={campaignTypes ? campaignTypes : []}
                  value={this.state.campaignType != null ? this.state.campaignType : null}
                  renderInput={(params) => <TextField {...params}
                    value={this.state.campaignType != null ? this.state.campaignType : null}
                    label={<span><span style={{ color: "red" }}></span>Loại chiến dịch</span>}
                    variant="outlined"
                    size="small"
                  />}
                  onChange={(event, value) => {
                    this.setState({ campaignType: value })
                  }}
                  getOptionLabel={(option) => option.name}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={6} xs={6}>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                  <KeyboardDatePicker
                    width="100%"
                    margin="none"
                    id="mui-pickers-date"
                    format="dd/MM/yyyy"
                    label="Ngày bắt đầu"
                    inputVariant="standard"
                    type="text"
                    autoOk={true}
                    value={startDate}
                    onChange={date => this.handleDateChange(date, "startDate")}
                    InputLabelProps={{
                      shrink: true,
                    }}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </MuiPickersUtilsProvider></Grid>
              <Grid item sm={6} xs={6}>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                  <KeyboardDatePicker
                    margin="none"
                    id="mui-pickers-date"
                    format="dd/MM/yyyy"
                    label="Ngày kết thúc"
                    inputVariant="standard"
                    type="text"
                    autoOk={true}
                    value={endDate}
                    onChange={date => this.handleDateChange(date, "endDate")}
                    InputLabelProps={{
                      shrink: true,
                    }}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />

                </MuiPickersUtilsProvider>
              </Grid>

              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Campaign.epc")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="epc"
                  value={epc}
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Campaign.cvr")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="cvr"
                  value={cvr}
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Campaign.commission")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="commission"
                  value={commission}
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Campaign.approvedRate30Days")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="approved_rate_30days"
                  value={approved_rate_30days}
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={12} xs={12}>
                <Autocomplete
                  id="combo-box"
                  options={products ? products : []}
                  value={this.state.product != null ? this.state.product : null}
                  renderInput={(params) => <TextField {...params}
                    value={this.state.product != null ? this.state.product : null}
                    label={<span><span style={{ color: "red" }}></span>Sản phẩm</span>}
                    variant="outlined"
                    size="small"
                  />}
                  onChange={(event, value) => {
                    this.setState({ product: value })
                  }}
                  getOptionLabel={(option) => option.name}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>
            </Grid>
          </DialogContent>
          <DialogActions>
            <div className="flex flex-space-between flex-middle mt-12">
              <Button
                variant="contained"
                className="mr-12"
                color="secondary"
                onClick={() => this.props.handleClose()}
              >
                {t("general.cancel")}
              </Button>
              <Button
                variant="contained"
                style={{ marginRight: "15px" }}
                color="primary"
                type="submit"
              >
                {t("general.save")}
              </Button>
            </div>
          </DialogActions>
        </ValidatorForm>
      </Dialog>
    );
  }
}

export default CampaignDialog;
