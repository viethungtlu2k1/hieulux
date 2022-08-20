// import React, { Component } from "react";
// import { Button, TextField, Grid, InputAdornment } from "@material-ui/core";
// import { Calendar, Views, momentLocalizer } from "react-big-calendar";
// import "react-big-calendar/lib/css/react-big-calendar.css";
// import "react-big-calendar/lib/addons/dragAndDrop/styles.css";
// import withDragAndDrop from "react-big-calendar/lib/addons/dragAndDrop";
// import moment from "moment";
// import { searchByPage } from "../Holiday/HolidayService";
// import CalendarHeader from "./CalendarHeader";
// import * as ReactDOM from "react-dom";
// import { Breadcrumb } from "egret";
// import { typeTime } from "../../role";
// import SelectStaffPopup from "./SelectStaffPopup";
// import { getAllEvents, updateEvent } from "./CalendarService";
// import EventEditorDialog from "./EventEditorDialog";
// import { getStaffByUserId } from "../Staff/StaffService";
// import { searchByPage as searchByPageTimeSheet } from "../TimeSheet/TimeSheetService";
// import localStorageService from "app/services/localStorageService";
// import InfoDialog from "./InfoDialog";
// import TimeSheetDialog from "../TimeSheet/TimeSheetDialog";

// const localizer = momentLocalizer(moment);

// const DragAndDropCalendar = withDragAndDrop(Calendar);

// let viewList = Object.keys(Views).map((key) => Views[key]);

// class EgretCalendar extends Component {
//   state = {
//     events: [],
//     shouldShowEventDialog: false,
//     newEvent: null,
//     rowsPerPage: 99999,
//     page: 0,
//   };

//   constructor(props) {
//     super(props);
//     this.headerComponentRef = React.createRef();
//   }

//   componentDidMount() {
//     this.updatePageData();

//     const nowUser = localStorageService.getLoginUser();
//     this.setState({ roles: nowUser.roles })
//     getStaffByUserId(nowUser.id).then(res => {
//       this.setState({ id: res.data.id }, () => {
//         this.updatePageData();
//       })
//     })

//   }

//   updateCalendar = () => {
//     getAllEvents()
//       .then((res) => res.data)
//       .then((events) => {
//         this.setState({ events });
//       });
//   };

//   handleDialogClose = () => {
//     this.setState({ shouldShowEventDialog: false });
//     this.updateCalendar();
//     this.setState({ shouldShowEventDialog: false, shouldOpenEditorDialog: false });
//     this.updatePageData();
//   };

//   handleEventMove = (event) => {
//     this.handleEventResize(event);
//   };

//   handleEventResize = (event) => {
//     updateEvent(event).then(() => {
//       this.updateCalendar();
//       this.updatePageData();
//     });
//   };

//   openNewEventDialog = ({ action, ...event }) => {
//     if (action === "doubleClick") {
//       this.setState({
//         newEvent: event,
//         shouldShowEventDialog: true,
//         shouldShowTimeSheetDialog: true,
//       });
//     }
//   };

//   openExistingEventDialog = (event) => {
//     this.setState({
//       newEvent: event,
//       shouldShowEventDialog: true,
//     });
//   };

//   search() {
//     this.setState({ page: 0 }, function () {
//       var searchObject = {};
//       searchObject.keyword = this.state.keyword;
//       searchObject.id = this.state.id
//       searchObject.pageIndex = this.state.page + 1;
//       searchObject.pageSize = this.state.rowsPerPage;
//       searchByPage(searchObject)
//         .then((res) => {
//           this.setState({
//             itemList: [...res.data.content],
//             totalElements: res.data.totalElements,
//           });
//         })
//         .catch((err) => {
//           console.log(err);
//         });
//     });
//   }

//   updatePageData = () => {
//     this.setState({events:[]})
//     var searchObject = {};
//     searchObject.keyword = "";
//     searchObject.id = this.state.id
//     searchObject.pageIndex = this.state.page + 1;
//     searchObject.pageSize = this.state.rowsPerPage;
//     searchObject.pageSize = 9999;
//     searchByPage(searchObject).then(({ data }) => {
//       this.setState({
//         itemList: [...data.content],
//         totalElements: data.totalElements,
//       });
//       this.getDataHoliday([...data.content]);
//     });

//     searchByPageTimeSheet(searchObject).then(({ data }) => {
//       this.setState({
//         itemList: [...data.content],
//         totalElements: data.totalElements,
//       });
//       this.getDataCalendar([...data.content]);
//     });
//   };



//   getDataCalendar = (itemList) => {
//     let events = this.state.events
//     itemList.forEach(element => {
//       let object1 = {}
//       object1.type = 1
//       object1.typeTime = 1;
//       object1.title = element.project.code + ": " + element.percentWork + "%"
//       object1.start = element.date
//       object1.end = element.date
//       object1.info = element
//       events.push(object1)
//     });
//     this.setState({ events });
//   }


//   setPage = (page) => {
//     this.setState({ page }, function () {
//       this.updatePageData();
//     });
//   };


//   openSelectStaffPopup = () => {
//     this.setState({ shouldOpenSelectStaffPopup: true });
//   };
//   handleSelectStaffPopupClose = () => {
//     this.setState({ shouldOpenSelectStaffPopup: false });
//   };

//   handleSelectStaff = (staffc) => {
//     this.setState({
//       staff: staffc,
//       id: staffc.id
//     }, () => {
//       this.handleSelectStaffPopupClose();
//       this.updatePageData();
//     });
//   };

//   getDataHoliday = (itemList) => {
//     // let events = [];
//     let events = this.state.events;
//     /*Dò từng mục */
//     itemList.forEach((element) => {
//       let object = {};
//       object.title = element.name;
//       object.start = element.startdate;
//       object.end = element.enddate;
//       events.push(object);
//     });
//     this.setState({ events });
//     console.log(this.state.events);
//   };

//   eventStyleGetter = (event, start, end, isSelected) => {
//     var backgroundColor = '#3333ff';
//     if (event.type == typeTime.Gio_bat_dau) {
//       backgroundColor = '#ffdc73'
//     } if (event.type == typeTime.Gio_ket_thuc) {
//       backgroundColor = '#1be109'
//     } if (event.type == typeTime.Di_lam_muon) {
//       backgroundColor = '#990099'
//     } if (event.type == typeTime.Nghi_phep) {
//       backgroundColor = '#ccffff'
//     } if (event.type == typeTime.Dang_cho_duyet) {
//       backgroundColor = '#ffd9b3'
//     }
//     if (event.type == typeTime.Tu_choi_nghi) {
//       backgroundColor = '#ff0000'
//     }
//     if (event.type == typeTime.Ngay_nghi_le) {
//       backgroundColor = '#ffccff'
//     }
//     var style = {
//       backgroundColor: backgroundColor,
//       borderRadius: '0px',
//       opacity: 0.8,
//       color: 'black',
//       border: '1px',
//       display: 'block'
//     };
//     return {
//       style: style
//     };
//   }

//   render() {
//     let { t, i18n } = this.props;
//     let { events, newEvent, shouldShowEventDialog, shouldOpenEditorDialog, roles, shouldOpenSelectStaffPopup, staff } = this.state;
//     return (
//       <div className="m-sm-30">
//         <div className="mb-sm-30">
//           <Breadcrumb routeSegments={[{ name: "Calendar" }]} />
//         </div>

//         <Button
//           className="mb-16"
//           variant="contained"
//           color="secondary"
//           onClick={() =>
//             this.openNewEventDialog({
//               action: "doubleClick",
//               start: new Date(),
//               end: new Date(),
//             })}></Button>
//           {roles ?.slice(0, 1).map(element => {
//             if (element.name != "ROLE_STAFF") {
//               return <>
//                 <Grid container spacing={2}>
//                   <Grid item md={4} sm={6} xs={12}>
//                     <TextField
//                       label={
//                         <span>
//                           Chọn nhân viên
//                         </span>
//                       }
//                       className="w-100 mt-16"
//                       errorMessages={["This field is required"]}
//                       variant="outlined"
//                       size="small"
//                       value={staff?.name != null && staff.name != "" ? staff.name : ""}
//                       InputProps={{
//                         readOnly: true,
//                         endAdornment: (
//                           <InputAdornment position="end">
//                             <Button
//                               className="h-100"
//                               style={{ position: "absolute", top: "0", right: "0" }}
//                               variant="contained"
//                               color="primary"
//                               onClick={() => {
//                                 this.setState({ shouldOpenSelectStaffPopup: true })
//                               }}
//                             >
//                               {t("general.select")}
//                             </Button>
//                           </InputAdornment>
//                         )
//                       }}
//                     />
//                     {shouldOpenSelectStaffPopup && (
//                       <SelectStaffPopup
//                         open={shouldOpenSelectStaffPopup}
//                         handleSelect={this.handleSelectStaff}
//                         selectedItem={staff ? staff : {}}
//                         handleClose={this.handleSelectStaffPopupClose}
//                         t={t}
//                         i18n={i18n}
//                       />
//                     )}

//                   </Grid>
//                 </Grid>
//               </>
//             }
            
//             // Add Event
            

//             if (element.name == "ROLE_STAFF") {
//               return <>
//                 <Button
//                   className="mb-16"
//                   variant="contained"
//                   color="secondary"
//                   onClick={() =>
//                     this.setState({ shouldOpenEditorDialog: true })
//                   }
//                 >
//                   Thêm công việc
//                   </Button>
//               </>
//             }
//           })}
        


//         <div className="h-700 flex-column">
//           <div style={{ display: "flex", alignItems: "center" }}>
//             Chú thích: 
//             <p style={{ backgroundColor: "#3333ff", maxWidth: "100px" }}>  &nbsp;Ngày nghỉ lễ  &nbsp;</p>
//             <p style={{ backgroundColor: "#9999ff", maxWidth: "100px" }}>  &nbsp;Đi làm muộn &nbsp;</p>
//             <p style={{ backgroundColor: "#ccffff", maxWidth: "100px" }}>  &nbsp;Nghỉ phép &nbsp;</p>
//             <p style={{ backgroundColor: "#1be109", maxWidth: "100px" }}>  &nbsp;Giờ kết thúc &nbsp;</p>
//             <p style={{ backgroundColor: "#ffd9b3", maxWidth: "120px" }}>  &nbsp;Công việc &nbsp;</p>
//             <p style={{ backgroundColor: "#ff0000", maxWidth: "100px" }}>  &nbsp;Từ chối nghỉ &nbsp;</p>
//           </div> <div ref={this.headerComponentRef} />
//           <div ref={this.headerComponentRef} />
//           <DragAndDropCalendar
//             selectable
             
//             localizer={localizer}
//             events={events}
//             onEventDrop={this.handleEventMove}
//             resizable
//             eventPropGetter={this.eventStyleGetter}
//             onEventResize={this.handleEventResize}
//             defaultView={Views.MONTH}
//             defaultDate={new Date()}
//             startAccessor="start"
//             endAccessor="end"
//             views={viewList}
//             step={60}
//             showMultiDayTimes
//             style={{ minHeight: 1000 }}
//             components={{
//               toolbar: (props) => {
//                 return this.headerComponentRef.current ? (
//                   ReactDOM.createPortal(
//                     <CalendarHeader {...props} />,
//                     this.headerComponentRef.current
//                   )
//                 ) : (
//                   <div>Header component not found</div>
//                 );
//               },
//             }}
//             // onNavigate={this.handleNavigate}
//             onSelectEvent={(event) => {
//               this.openExistingEventDialog(event);
//             }}
//             onSelectSlot={(slotDetails) => this.openNewEventDialog(slotDetails)}
//             onShowMore={(events, date) => this.setState({ showModal: true, events })}
  
//           />


//         </div>
//         {shouldShowEventDialog && (
//           <EventEditorDialog
//             handleClose={this.handleDialogClose}
//             open={shouldShowEventDialog}
//             event={newEvent}
//           />
//         )}
//         {
//           shouldShowEventDialog && (
//             <InfoDialog
//               handleClose={this.handleDialogClose}
//               open={shouldShowEventDialog}
//               event={newEvent}
//             />
//           )
//         }


//         {
//           this.state.shouldOpenEditorDialog && (
//             <TimeSheetDialog
//               t={t}
//               i18n={i18n}
//               handleClose={this.handleDialogClose}
//               open={this.state.shouldOpenEditorDialog}
//               handleOKEditClose={this.handleOKEditClose}
//               item={this.state.item}
//             />
//           )
//         }
        
//       </div>
//     );
//   }
// }

// export default EgretCalendar;

import React, { Component } from "react";
import { Button, TextField, Grid, InputAdornment } from "@material-ui/core";
import { Calendar, Views, momentLocalizer } from "react-big-calendar";
import "react-big-calendar/lib/css/react-big-calendar.css";
import "react-big-calendar/lib/addons/dragAndDrop/styles.css";
import withDragAndDrop from "react-big-calendar/lib/addons/dragAndDrop";
import moment from "moment";
import { searchByPage } from "../Holiday/HolidayService";
import CalendarHeader from "./CalendarHeader";
import * as ReactDOM from "react-dom";
import { Breadcrumb } from "egret";
import { typeTime } from "../../role";
import SelectStaffPopup from "./SelectStaffPopup";
import { getAllEvents, updateEvent } from "./CalendarService";
import EventEditorDialog from "./EventEditorDialog";
import { getStaffByUserId } from "../Staff/StaffService";
import { searchByPage as searchByPageTimeSheet } from "../TimeSheet/TimeSheetService";
import localStorageService from "app/services/localStorageService";
import InfoDialog from "./InfoDialog";
import TimeSheetDialog from "../TimeSheet/TimeSheetDialog";

const localizer = momentLocalizer(moment);

const DragAndDropCalendar = withDragAndDrop(Calendar);

let viewList = Object.keys(Views).map((key) => Views[key]);

class EgretCalendar extends Component {
  state = {
    events: [],
    shouldShowEventDialog: false,
    newEvent: null,
    rowsPerPage: 99999,
    page: 0,
  };

  constructor(props) {
    super(props);
    this.headerComponentRef = React.createRef();
  }

  componentDidMount() {
    this.updatePageData();

    const nowUser = localStorageService.getLoginUser();
    this.setState({ roles: nowUser.roles })
    getStaffByUserId(nowUser.id).then(res => {
      this.setState({ id: res.data.id }, () => {
        this.updatePageData();
      })
    })

  }

  updateCalendar = () => {
    getAllEvents()
      .then((res) => res.data)
      .then((events) => {
        this.setState({ events });
      });
  };

  handleDialogClose = () => {
    this.setState({ shouldShowEventDialog: false });
    this.updateCalendar();
    this.setState({ shouldShowEventDialog: false, shouldOpenEditorDialog: false });
    this.updatePageData();
  };

  handleEventMove = (event) => {
    this.handleEventResize(event);
  };

  handleEventResize = (event) => {
    updateEvent(event).then(() => {
      this.updateCalendar();
      this.updatePageData();
    });
  };

  openNewEventDialog = ({ action, ...event }) => {
    if (action === "doubleClick") {
      this.setState({
        newEvent: event,
        shouldShowEventDialog: true,
        shouldShowTimeSheetDialog: true,
      });
    }
  };

  openExistingEventDialog = (event) => {
    this.setState({
      newEvent: event,
      shouldShowEventDialog: true,
    });
  };

  search() {
    this.setState({ page: 0 }, function () {
      var searchObject = {};
      searchObject.keyword = this.state.keyword;
      searchObject.id = this.state.id
      searchObject.pageIndex = this.state.page + 1;
      searchObject.pageSize = this.state.rowsPerPage;
      searchByPage(searchObject)
        .then((res) => {
          this.setState({
            itemList: [...res.data.content],
            totalElements: res.data.totalElements,
          });
        })
        .catch((err) => {
          console.log(err);
        });
    });
  }

  updatePageData = () => {
    this.setState({events:[]})
    var searchObject = {};
    searchObject.keyword = "";
    searchObject.id = this.state.id
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchObject.pageSize = 9999;
    searchByPage(searchObject).then(({ data }) => {
      this.setState({
        itemList: [...data.content],
        totalElements: data.totalElements,
      });
      this.getDataHoliday([...data.content]);
    });

    searchByPageTimeSheet(searchObject).then(({ data }) => {
      this.setState({
        itemList: [...data.content],
        totalElements: data.totalElements,
      });
      this.getDataCalendar([...data.content]);
    });
  };



  getDataCalendar = (itemList) => {
    let events = this.state.events
    itemList.forEach(element => {
      let object1 = {}
      object1.type = 1
      object1.typeTime = 1;
      object1.title = element.project.code + ": " + element.percentWork + "%"
      object1.start = element.date
      object1.end = element.date
      object1.info = element
      events.push(object1)
    });
    this.setState({ events });
  }


  setPage = (page) => {
    this.setState({ page }, function () {
      this.updatePageData();
    });
  };


  openSelectStaffPopup = () => {
    this.setState({ shouldOpenSelectStaffPopup: true });
  };
  handleSelectStaffPopupClose = () => {
    this.setState({ shouldOpenSelectStaffPopup: false });
  };

  handleSelectStaff = (staffc) => {
    this.setState({
      staff: staffc,
      id: staffc.id
    }, () => {
      this.handleSelectStaffPopupClose();
      this.updatePageData();
    });
  };

  getDataHoliday = (itemList) => {
    // let events = [];
    let events = this.state.events;
    /*Dò từng mục */
    itemList.forEach((element) => {
      let object = {};
      object.title = element.name;
      object.start = element.startdate;
      object.end = element.enddate;
      events.push(object);
    });
    this.setState({ events });
    console.log(this.state.events);
  };

  eventStyleGetter = (event, start, end, isSelected) => {
    var backgroundColor = '#3333ff';
    if (event.type == typeTime.Gio_bat_dau) {
      backgroundColor = '#ffdc73'
    } if (event.type == typeTime.Gio_ket_thuc) {
      backgroundColor = '#1be109'
    } if (event.type == typeTime.Di_lam_muon) {
      backgroundColor = '#990099'
    } if (event.type == typeTime.Nghi_phep) {
      backgroundColor = '#ccffff'
    } if (event.type == typeTime.Dang_cho_duyet) {
      backgroundColor = '#ffd9b3'
    }
    if (event.type == typeTime.Tu_choi_nghi) {
      backgroundColor = '#ff0000'
    }
    if (event.type == typeTime.Ngay_nghi_le) {
      backgroundColor = '#ffccff'
    }
    var style = {
      backgroundColor: backgroundColor,
      borderRadius: '0px',
      opacity: 0.8,
      color: 'black',
      border: '1px',
      display: 'block'
    };
    return {
      style: style
    };
  }

  render() {
    let { t, i18n } = this.props;
    let { events, newEvent, shouldShowEventDialog, shouldOpenEditorDialog, roles, shouldOpenSelectStaffPopup, staff } = this.state;
    return (
      <div className="m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb routeSegments={[{ name: "Calendar" }]} />
        </div>

        <Button
          className="mb-16"
          variant="contained"
          color="secondary"
          onClick={() =>
            this.openNewEventDialog({
              action: "doubleClick",
              start: new Date(),
              end: new Date(),
            })}
          >Add Event</Button>
          {roles ?.slice(0, 1).map(element => {
            if (element.name != "ROLE_STAFF") {
              return <>
                <Grid container spacing={2}>
                  <Grid item md={4} sm={6} xs={12}>
                    <TextField
                      label={
                        <span>
                          Chọn nhân viên
                        </span>
                      }
                      className="w-100 mt-16"
                      errorMessages={["This field is required"]}
                      variant="outlined"
                      size="small"
                      value={staff?.name != null && staff.name != "" ? staff.name : ""}
                      InputProps={{
                        readOnly: true,
                        endAdornment: (
                          <InputAdornment position="end">
                            <Button
                              className="h-100"
                              style={{ position: "absolute", top: "0", right: "0" }}
                              variant="contained"
                              color="primary"
                              onClick={() => {
                                this.setState({ shouldOpenSelectStaffPopup: true })
                              }}
                            >
                              {t("general.select")}
                            </Button>
                          </InputAdornment>
                        )
                      }}
                    />
                    {shouldOpenSelectStaffPopup && (
                      <SelectStaffPopup
                        open={shouldOpenSelectStaffPopup}
                        handleSelect={this.handleSelectStaff}
                        selectedItem={staff ? staff : {}}
                        handleClose={this.handleSelectStaffPopupClose}
                        t={t}
                        i18n={i18n}
                      />
                    )}

                  </Grid>
                </Grid>
              </>
            }
            
            
            // Add Event
            

            if (element.name == "ROLE_STAFF") {
              return <>
                <Button
                  className="mb-16"
                  variant="contained"
                  color="secondary"
                  onClick={() =>
                    this.setState({ shouldOpenEditorDialog: true })
                  }
                >
                  Thêm công việc
                  </Button>
              </>
            }
          })}
        


        <div className="h-700 flex-column">
          <div style={{ display: "flex", alignItems: "center" }}>
            Chú thích: 
            <p style={{ backgroundColor: "#3333ff", maxWidth: "100px" }}>  &nbsp;Ngày nghỉ lễ  &nbsp;</p>
            <p style={{ backgroundColor: "#9999ff", maxWidth: "100px" }}>  &nbsp;Đi làm muộn &nbsp;</p>
            <p style={{ backgroundColor: "#ccffff", maxWidth: "100px" }}>  &nbsp;Nghỉ phép &nbsp;</p>
            <p style={{ backgroundColor: "#1be109", maxWidth: "100px" }}>  &nbsp;Giờ kết thúc &nbsp;</p>
            <p style={{ backgroundColor: "#ffd9b3", maxWidth: "120px" }}>  &nbsp;Công việc &nbsp;</p>
            <p style={{ backgroundColor: "#ff0000", maxWidth: "100px" }}>  &nbsp;Từ chối nghỉ &nbsp;</p>
          </div> <div ref={this.headerComponentRef} />
          <div ref={this.headerComponentRef} />
          <DragAndDropCalendar
            selectable
             
            localizer={localizer}
            events={events}
            onEventDrop={this.handleEventMove}
            resizable
            eventPropGetter={this.eventStyleGetter}
            onEventResize={this.handleEventResize}
            defaultView={Views.MONTH}
            defaultDate={new Date()}
            startAccessor="start"
            endAccessor="end"
            views={viewList}
            step={60}
            showMultiDayTimes
            style={{ minHeight: 1000 }}
            components={{
              toolbar: (props) => {
                return this.headerComponentRef.current ? (
                  ReactDOM.createPortal(
                    <CalendarHeader {...props} />,
                    this.headerComponentRef.current
                  )
                ) : (
                  <div>Header component not found</div>
                );
              },
            }}
            // onNavigate={this.handleNavigate}
            onSelectEvent={(event) => {
              this.openExistingEventDialog(event);
            }}
            onSelectSlot={(slotDetails) => this.openNewEventDialog(slotDetails)}
            onShowMore={(events, date) => this.setState({ showModal: true, events })}
  
          />


        </div>
        {shouldShowEventDialog && (
          <EventEditorDialog
            handleClose={this.handleDialogClose}
            open={shouldShowEventDialog}
            event={newEvent}
          />
        )}
        {
          shouldShowEventDialog && (
            <InfoDialog
              handleClose={this.handleDialogClose}
              open={shouldShowEventDialog}
              event={newEvent}
            />
          )
        }


        {
          this.state.shouldOpenEditorDialog && (
            <TimeSheetDialog
              t={t}
              i18n={i18n}
              handleClose={this.handleDialogClose}
              open={this.state.shouldOpenEditorDialog}
              handleOKEditClose={this.handleOKEditClose}
              item={this.state.item}
            />
          )
        }
        
      </div>
    );
  }
}

export default EgretCalendar;