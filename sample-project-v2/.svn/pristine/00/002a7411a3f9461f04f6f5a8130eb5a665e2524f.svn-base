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
} from "@material-ui/core";
// import Paper from '@material-ui/core/Paper'
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import Draggable from "react-draggable";
import NotificationPopup from "../Component/NotificationPopup/NotificationPopup";
import {
  saveItem,
  addItem,
  updateItem,
  checkCode,
  checkHoliday,
} from "./HolidayService";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { KeyboardDatePicker, MuiPickersUtilsProvider } from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
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

class HolidayDialog extends Component {
  state = {
    id: "",
    name: "",
    code: "",
    startDate: "",
    endDate: "",
    description: "",
    type: "",

    shouldOpenNotificationPopup: false,
    Notification: "",
  };

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

  handleFormSubmit = () => {
    var item = {};
    item = this.state;
    let { id } = this.state;
    let { code } = this.state;
    var { t } = this.props;
    var checkTime = moment(this.state.startDate).isBefore(this.state.endDate);
    var checkTime2 = new Date() <= (this.state.endDate);
    checkCode(id, code).then((result) => {
      //Nếu trả về true là code đã được sử dụng
      if (result.data) {
        toast.warning(t("general.dupli_code"));
        // alert("Code đã được sử dụng");
      } else {
        //Nếu trả về false là code chưa sử dụng có thể dùng
        // checkHoliday(this.state).then((result) => {         
        //   if (result.data) {
        //     toast.warning(t("general.dupli_holiday"));
        //   } else {
        if (id) {
          checkHoliday(this.state).then((result) => {
            if (result.data) {
              toast.warning(t("general.dupli_holiday"));
            } else {
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
            }
          });
        } else {
          checkHoliday(this.state).then((result) => {
            if (result.data) {
              toast.warning(t("general.dupli_holiday"));
            } else {
              if (checkTime) {
                saveItem({
                  ...this.state,
                }).then(() => {
                  toast.success(t("general.addSuccess"));
                  this.props.handleOKEditClose();
                });
              } else {
                toast.error("Thời gian bắt đầu phải sớm hơn thời gian kết thúc");
              }
            }
          });
        }
      }
    });
  };

  componentWillMount() {
    //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
    let { open, handleClose, item } = this.props;
    this.setState({ ...item });
  }

  render() {
    let {
      id,
      name,
      code,
      startDate,
      endDate,
      description,
      shouldOpenNotificationPopup,
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

              <Grid item sm={6} xs={6}>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                  <Grid container justify="space-around">
                    <KeyboardDatePicker
                      className="w-100"
                      margin="normal"
                      id="date-picker-dialog"
                      label={
                        <span>
                          <span style={{ color: "red" }}>*</span>
                          {t("general.startDate")}
                        </span>
                      }
                      format="dd/MM/yyyy"
                      // type = "String"
                      value={startDate}
                      onChange={(value) => {
                        this.setState({ startDate: value })
                      }}
                      InputLabelProps={{
                        shrink: true,
                      }}
                      KeyboardButtonProps={{
                        'aria-label': 'change date',
                      }}
                    />
                  </Grid>
                </MuiPickersUtilsProvider>
              </Grid>

              <Grid item sm={6} xs={6}>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                  <Grid container justify="space-around">
                    <KeyboardDatePicker
                      className="w-100"
                      margin="normal"
                      id="date-picker-dialog"
                      label={
                        <span>
                          <span style={{ color: "red" }}>*</span>
                          {t("general.endDate")}
                        </span>
                      }
                      format="dd/MM/yyyy"
                      // type = "String"
                      value={endDate}
                      onChange={(value) => {
                        this.setState({ endDate: value })
                      }}
                      InputLabelProps={{
                        shrink: true,
                      }}
                      KeyboardButtonProps={{
                        'aria-label': 'change date',
                      }}
                    />
                  </Grid>
                </MuiPickersUtilsProvider>
              </Grid>


              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("general.description")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="description"
                  value={description}
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

export default HolidayDialog;
