import React, { Component } from "react";
import { Dialog, IconButton, Button, Icon, Grid } from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { MuiPickersUtilsProvider, DateTimePicker } from "@material-ui/pickers";
import "date-fns";
import DateFnsUtils from "@date-io/date-fns";
import { addNewEvent, updateEvent, deleteEvent } from "./CalendarService";

export default class EventEditor extends Component {
  state = {
    title: "",
    startTime: "",
    endTime: "",
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
        this.props.handleOKClose();
      });
    } else {
      addNewEvent({
        ...this.state
      }).then(() => {
        this.props.handleClose();
        this.props.handleOKClose();
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
  handleDiaglogClose = () => {
    this.props.handleClose();
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
  } 

  render() {
    let { title, startTime, endTime, location, note, chairman } = this.state;
    let { open, handleClose, handleOKClose, event, t } = this.props;

    return (
      <div>
        <div className="flex flex-space-between flex-middle pl-16 pr-8 py-8 bg-primary">
          <h4 className="m-0 text-white">Chi tiết công việc</h4>
          <IconButton onClick={handleClose}>
            <Icon className="text-white">clear</Icon>
          </IconButton>
        </div>

        <div className="p-24">
        {title}
          <div className="flex flex-space-between flex-middle">

            <Button onClick={this.handleDiaglogClose} variant="contained" color="primary" type="button">
              {t('Cancel')}
            </Button>
          </div>
        </div>
      </div>
    );
  }
}

//export default EventEditorDialog;
