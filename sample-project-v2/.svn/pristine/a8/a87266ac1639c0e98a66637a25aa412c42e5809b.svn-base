import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  DialogActions,
  Paper,
  DialogTitle,
  DialogContent,
} from "@material-ui/core";
// import Paper from '@material-ui/core/Paper'
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import Draggable from "react-draggable";
import {
  saveItem,
  updateItem,
  checkName,
  getAllNational,
  getAllCategory,
  getDetailWithCategory,
  getDetailWithNationaly
} from "./SpaceService";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { TextField } from "@material-ui/core";

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


class AdSpaceDialog extends Component {
  state = {
    id: "",
    name: "",
    channel: 1,
    channelName: "",
    network: "",
    url_page: "",
    follower: "",
    likeAmount: "",
    description: "",
    experience: "",
    urlWebsite: "",
    urlYoutube: "",
    nameYoutube: "",
    subscribeYoutube: "",
    contextualAdvertisingSystem: 1,
    contextualAdvertisingSystemName: "",
    nationaly: [],
    category: [],
    listNational: [],
    listCategory: [],
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
    let { id, name } = this.state;
    var { t } = this.props;
    let space = {
      id: this.state.id,
      name: this.state.name,
      channel: this.state.channel,
      network: this.state.network,
      url_page: this.state.url_page,
      follower: this.state.follower,
      likeAmount: this.state.likeAmount,
      description: this.state.description,
      experience: this.state.experience,
      urlWebsite: this.state.urlWebsite,
      urlYoutube: this.state.urlYoutube,
      nameYoutube: this.state.nameYoutube,
      subscribeYoutube: this.state.subscribeYoutube,
      contextualAdvertisingSystem: this.state.contextualAdvertisingSystem,
      nationaly: this.state.nationaly,
      category: this.state.category,
    }

    checkName(id, name).then((res) => {
      if (res.data === true) {
        toast.warning(t("general.dupli_code"));
      } else {
        if (id) {
          updateItem({
            ...space,
          }).then(() => {
            toast.success(t("general.updateSuccess"));
            this.props.handleOKEditClose();
          });
        } else {

          saveItem({
            ...space,
          }).then(() => {
            toast.success(t("general.addSuccess"));
            this.props.handleOKEditClose();
          });
        }
      }
    })
  }

  componentWillMount() {
    //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
    let { open, handleClose, item } = this.props;
    console.log(item);
    this.setState({ ...item });
  }

  componentDidMount() {
    this.getData();
    if (this.props.item !== null) {
      this.getDetailAdSpace();
    }
  }

  getData = () => {
    getAllNational()
      .then(res => {
        this.setState({
          listNational: res.data
        })
      })
      .catch(err => console.log(err));

    getAllCategory()
      .then(res => {
        this.setState({
          listCategory: res.data
        })
      })
      .catch(err => console.log(err));
  }

  getDetailAdSpace = () => {
    const { item } = this.props;
    if (item !== null) {
      getDetailWithNationaly(item.id)
        .then(res => {
          this.setState({
            nationaly: res.data.map(item => {
              return item.nationaly
            })
          })
        })
        .catch(err => console.log(err));

      getDetailWithCategory(item.id)
        .then((res) => {
          this.setState({
            category: res.data.map(item => {
              return item.category
            })
          })
        })
        .catch(err => console.log(err));
    }
  }

  render() {

    let {
      id,
      name,
      channel,
      channelName,
      network,
      url_page,
      follower,
      likeAmount,
      description,
      experience,
      urlWebsite,
      urlYoutube,
      nameYoutube,
      subscribeYoutube,
      contextualAdvertisingSystem,
      contextualAdvertisingSystemName
    } = this.state;


    let { open, t } = this.props;

    const channels = [
      {
        title: "Webiste",
        value: 1
      },
      {
        title: "Contextual_Advertiser",
        value: 2
      },
      {
        title: "Social_Netword",
        value: 3
      },
      {
        title: "Youtube_channel",
        value: 4
      },
      // "Webiste",
      // "Contextual_Advertiser",
      // "Social_Netword",
      // "Youtube_channel"
    ]

    const networks = [
      {
        title: "Facebook",
        value: "Facebook"
      },
      {
        title: "Zalo",
        value: "Zalo"
      },
      {
        title: "Tiktok",
        value: "Tiktok"
      },
      {
        title: "Other",
        value: "Youtube"
      }
      // "Facebook",
      // "Zalo",
      // "Tiktok",
      // "Youtube"
    ]

    const listAds = [
      {
        title: "Bing Ads",
        value: 1
      },
      {
        title: "Google Ads",
        value: 2
      },
      {
        title: "Facebook Ads",
        value: 3
      },
      {
        title: "Tiktok Ads",
        value: 4
      },
      {
        title: "Zalo Ads",
        value: 5
      },
    ]

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
          <span className="">{id ? t("general.update") : t("general.addNew")}</span>
        </DialogTitle>

        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
          <DialogContent>
            <Grid className="" container spacing={2}>

              {/* Channel */}
              <Grid item sm={12} xs={12}>

                <Autocomplete
                  id="combo-box-demo"
                  className="w-100"
                  options={channels}
                  defaultValue={{
                    title: channelName ? channelName : "Website",
                    value: channel
                  }}
                  onClick={() => alert("ZZZ")}
                  getOptionLabel={(option) => option.title}
                  onChange={(event, value) => this.setState({
                    channel: value.value
                  })}
                  renderInput={(params) => <TextField {...params} label="Kênh" name="channel" value={channel} variant="outlined" />}
                />

              </Grid>

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
                      {t("general.experience")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="experience"
                  value={experience}
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


              <Grid item sm={12} xs={12}>
                <Autocomplete
                  multiple
                  id="tags-standard"
                  className="w-100"
                  options={this.state.listNational}
                  getOptionLabel={(option) => option.name}
                  defaultValue={
                    this.state.nationaly
                  }
                  value={
                    this.state.nationaly
                  }
                  onChange={(event, value) => this.setState({
                    nationaly: value
                  })}
                  renderInput={(params) => <TextField {...params} label="Quốc gia" name="nationaly" variant="outlined" />}
                />
              </Grid>

              <Grid item sm={12} xs={12}>
                <Autocomplete
                  multiple
                  id="tags-standard"
                  className="w-100"
                  options={this.state.listCategory}
                  defaultValue={
                    this.state.category
                  }
                  value={
                    this.state.category
                  }
                  getOptionLabel={(option) => option.name}
                  onChange={(event, value) => this.setState({
                    category: value
                  })}
                  renderInput={(params) => <TextField {...params} label="Danh mục" name="category" variant="outlined" />}
                />
              </Grid>


              {/* ----------------------   Channel type 1 website   -------------------------------------- */}

              {
                channel === 1 ? <Grid item sm={12} xs={12}>
                  <TextValidator
                    className="w-100 "
                    label={
                      <span>
                        <span style={{ color: "red" }}>*</span>
                        {t("general.urlWebsite")}
                      </span>
                    }
                    onChange={this.handleChange}
                    type="text"
                    name="urlWebsite"
                    value={urlWebsite}
                    validators={["required"]}
                    errorMessages={[t("general.required")]}
                  />
                </Grid> : ""
              }

              {/* ------------------------------------------------------------- */}



              {/* ----------------------    Channel type 2 contextxual   --------------------------------------- */}

              {
                channel === 2 ? <>
                  <Grid item sm={12} xs={12}>
                    <Autocomplete
                      id="combo-box-demo"
                      className="w-100"
                      options={listAds}
                      defaultValue={
                        {
                          title: contextualAdvertisingSystemName,
                          value: contextualAdvertisingSystem
                        }
                      }
                      getOptionLabel={(option) => option.title}
                      onChange={(event, value) => this.setState({
                        contextualAdvertisingSystem: value.value
                      })}
                      renderInput={(params) => <TextField {...params} label="Hệ thống quảng cáo" name="contextualAdvertisingSystem" variant="outlined" />}
                    />
                  </Grid>
                </> : ""

              }



              {/* ------------------------------------------------------------- */}


              {/* -------------------------  Channel type 3   ------------------------------------ */}

              {
                channel === 3 ? <>
                  <Grid item sm={12} xs={12}>
                    <Autocomplete
                      id="combo-box-demo"
                      className="w-100"
                      options={networks}
                      defaultValue={
                        {
                          title: network,
                          value: network
                        }
                      }
                      getOptionLabel={(option) => option.title}
                      onChange={(event, value) => this.setState({
                        network: value.value
                      })}
                      renderInput={(params) => <TextField {...params} label="Mạng xã hội" name="network" variant="outlined" />}
                    />

                  </Grid>
                  <Grid item sm={12} xs={12}>
                    <TextValidator
                      className="w-100 "
                      label={
                        <span>
                          <span style={{ color: "red" }}>*</span>
                          {t("general.url_page")}
                        </span>
                      }
                      onChange={this.handleChange}
                      type="text"
                      name="url_page"
                      value={url_page}
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
                          {t("general.follower")}
                        </span>
                      }
                      onChange={this.handleChange}
                      type="number"
                      name="follower"
                      value={follower}
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
                          {t("general.likeAmount")}
                        </span>
                      }
                      onChange={this.handleChange}
                      type="number"
                      name="likeAmount"
                      value={likeAmount}
                      validators={["required"]}
                      errorMessages={[t("general.required")]}
                    />
                  </Grid>
                </> : ""
              }



              {/* ------------------------------------------------------------- */}

              {/* ------------------------   Channel type 4 youtube   ------------------------------------- */}

              {
                channel === 4 ? <>
                  <Grid item sm={12} xs={12}>
                    <TextValidator
                      className="w-100 "
                      label={
                        <span>
                          <span style={{ color: "red" }}>*</span>
                          {t("general.urlYoutube")}
                        </span>
                      }
                      onChange={this.handleChange}
                      type="text"
                      name="urlYoutube"
                      value={urlYoutube}
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
                          {t("general.nameYoutube")}
                        </span>
                      }
                      onChange={this.handleChange}
                      type="text"
                      name="nameYoutube"
                      value={nameYoutube}
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
                          {t("general.subscribeYoutube")}
                        </span>
                      }
                      onChange={this.handleChange}
                      type="number"
                      name="subscribeYoutube"
                      value={subscribeYoutube}
                      validators={["required"]}
                      errorMessages={[t("general.required")]}
                    />
                  </Grid>
                </> : ""
              }



              {/* ------------------------------------------------------------- */}

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

export default AdSpaceDialog;
