import React, { Component } from "react";
import { Dialog, IconButton, Button, Icon, Grid } from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { MuiPickersUtilsProvider, DateTimePicker } from "@material-ui/pickers";
import "date-fns";
import DateFnsUtils from "@date-io/date-fns";
import { addNewEvent, updateEvent, deleteEvent } from "./CalendarService";
import moment from "moment";

class EventEditorDialog extends Component {
  state = {
    title: "",
    start: "",
    end: "",
    location: "",
    note: ""
  };

  handleChange = event => {
    event.persist();
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleFormSubmit = () => {
    let { id } = this.state;
    if (id) {
      updateEvent({
        ...this.state
      }).then(() => {
        this.props.handleClose();
      });
    } else {
      addNewEvent({
        id: this.generateRandomId(),
        ...this.state
      }).then(() => {
        this.props.handleClose();
      });
    }
  };

  handleDeleteEvent = () => {
    if (this.state.id) {
      deleteEvent(this.state).then(() => {
        this.props.handleClose();
      });
    }
  };

  handleDateChange = (date, name) => {
    this.setState({
      [name]: date
    });
  };

  generateRandomId = () => {
    let tempId = Math.random().toString();
    let id = tempId.substr(2, tempId.length - 1);
    return id;
  };

  componentWillMount() {
    this.setState({
      ...this.props.event
    });
    console.log(this.props.event);
  }

  render() {
    let { title, start, end, location, note, info } = this.state;
    let { open, handleClose } = this.props;

    return (
      <Dialog onClose={handleClose} open={open} maxWidth="xs" fullWidth={true}>
        <div className="flex flex-space-between flex-middle pl-16 pr-8 py-8 bg-primary">
          <h4 className="m-0 text-white">Chi tiết công việc</h4>
          <IconButton onClick={handleClose}>
            <Icon className="text-white">clear</Icon>
          </IconButton>
        </div>

        <div className="p-16">
          Ngày làm viêc:  
            {moment(info?.date).format('YYYY-MM-DD')}<br />
          Dự án: {info?.project.name}<br />
          Loại công việc: {info?.jobtype.name}<br />
         Phần trăm công việc trong 1 ngày : {info?.percentWork} %<br />
         Mô tả: {info?.describe}
          <div className="flex flex-space-between flex-middle">

            <hr />
            <Button onClick={handleClose} variant="contained" color="primary" type="button">
              Đóng
            </Button>
          </div>
        </div>
      </Dialog>
    );
  }
}

export default EventEditorDialog;
