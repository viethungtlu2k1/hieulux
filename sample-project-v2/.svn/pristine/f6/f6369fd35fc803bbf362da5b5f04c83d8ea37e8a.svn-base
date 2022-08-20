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
import Autocomplete from "@material-ui/lab/Autocomplete";
import Draggable from "react-draggable";
import NotificationPopup from "../Component/NotificationPopup/NotificationPopup";
import {
    saveItem,
  addItem,
  updateItem,
  checkCode,
  getAllCategory,
  getAllNationaly,
  getAllCurrency,
  getAllCampaignType,
  uploadImage
} from "./ProductService";
import UploadImage from "../forms/UploadImage";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

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

class ProductDialog extends Component {
  state = {
    id: "",
    name: "",
    code: "",
    price: "",
    payout: "",
    description: "",
    mainImageUrl: "",
    imagePreviewUrl : "",
    file : null,
    type: "",
    category: {},
    categoryList: [],
    nationalyList: [],
    nationaly: {},
    currencyList: [],
    currencyPrice: {},
    currencyPayout: {},
    campaignType: {},
    campaignTypeList: [],
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

  selectCategory = (categorySelected) => {
    console.log(categorySelected);
    this.setState({ category: categorySelected }, function () {});
  };

  handleImageSelect = (files) => {
    console.log(file)
    let file = files[0];
    let reader = new FileReader();

    reader.onloadend = () => {
      this.setState({
        file: file,
        imagePreviewUrl: [reader.result],
      });
    };

    reader.readAsDataURL(file);
  };

  handleImageRemove = () => {
    this.setState({
      file: null,
      imagePreviewUrl: "",
    });
  };

  handleFormSubmit = () => {
    let { id } = this.state;
    let { code } = this.state;
    var { t } = this.props;
    checkCode(id, code).then((result) => {
      //Nếu trả về true là code đã được sử dụng
      if (result.data) {
        toast.warning(t("general.dupli_code"));
        // alert("Code đã được sử dụng");
      } else {
        //Nếu trả về false là code chưa sử dụng có thể dùng
        if (id) {
          updateItem({
            ...this.state,
          }).then((res) => {
            toast.success(t("general.updateSuccess"));
            this.props.handleOKEditClose();
            if (this.state.file)
              uploadImage(this.state.file, res.data.id);
          });
        } else {
            saveItem({
            ...this.state,
          }).then((res) => {
            toast.success(t("general.addSuccess"));
            this.props.handleOKEditClose();
            if (this.state.file)
              uploadImage(this.state.file, res.data.id);
          });
        }
      }
    });
  };

  componentDidMount() {
    getAllCategory()
      .then(({ data }) => {
        this.setState({
          categoryList: [...data.content],
          // totalElements: data.totalElements,
        });
      })
      .catch((err) => {
        toast.warning(err + "");
      });
    getAllNationaly()
      .then(({ data }) => {
        this.setState({
          nationalyList: [...data.content],
          // totalElements: data.totalElements,
        });
      })
      .catch((err) => {
        toast.warning(err + "");
      });
    getAllCurrency()
      .then(({ data }) => {
        this.setState({
          currencyList: [...data.content],
          // totalElements: data.totalElements,
        });
      })
      .catch((err) => {
        toast.warning(err + "");
      });
    getAllCampaignType()
      .then(({ data }) => {
        this.setState({
          campaignTypeList: [...data.content],
          // totalElements: data.totalElements,
        });
      })
      .catch((err) => {
        toast.warning(err + "");
      });
  }

  componentWillMount() {
    //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
    let { open, handleClose, item } = this.props;
    this.setState({...item});
  }

  render() {
    let {
      id,
      name,
      code,
      price,
      payout,
      description,
      mainImageUrl,
      category,
      categoryList,
      nationaly,
      nationalyList,
      imagePreviewUrl,
      currencyList,
      currencyPrice,
      currencyPayout,
      campaignType,
      campaignTypeList,
      shouldOpenNotificationPopup,
    } = this.state;
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;

    console.log('item', this.props.item)
    console.log('pl', this.state)

    return (
      <Dialog
        open={open}
        PaperComponent={PaperComponent}
        maxWidth="md"
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
                <UploadImage
                  className="w-30"
                  handleImageSelect={this.handleImageSelect}
                  handleImageRemove={this.handleImageRemove}
                  mainImageUrl={mainImageUrl}
                  imagePreviewUrl={imagePreviewUrl}
                  t={t}
                />
              </Grid>

              <Grid item sm={6} xs={6}>
                <TextValidator
                  className="w-100"
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

              <Grid item sm={6} xs={6}>
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
                {categoryList && (
                  <Autocomplete
                    options={categoryList}
                    defaultValue={category}
                    disableCloseOnSelect
                    getOptionSelected={(option, value) =>
                      option.id === value.id
                    }
                    getOptionLabel={(option) => option.name}
                    onChange={(event, value) => {
                      this.setState({ category: value });
                    }}
                    style={{ width: "100%" }}
                    renderInput={(params) => (
                      <TextValidator
                        {...params}
                        value={category||""}
                        label={
                          <span>
                            <span style={{ color: "red" }}> * </span>
                            {t("Loại sản phẩm")}
                          </span>
                        }
                        fullWidth
                        validators={["required"]}
                        errorMessages={[t("Validation.this_field_is_required")]}
                      />
                    )}
                  />
                )}
              </Grid>

              <Grid item sm={12} xs={12}>
                {nationalyList && (
                  <Autocomplete
                    options={nationalyList}
                    defaultValue={nationaly}
                    disableCloseOnSelect
                    getOptionSelected={(option, value) =>
                      option.id === value.id
                    }
                    getOptionLabel={(option) => option.name}
                    onChange={(event, value) => {
                      this.setState({ nationaly: value });
                    }}
                    style={{ width: "100%" }}
                    renderInput={(params) => (
                      <TextValidator
                        {...params}
                        value={nationaly||""}
                        label={
                          <span>
                            <span style={{ color: "red" }}> * </span>
                            {t("Quốc gia")}
                          </span>
                        }
                        fullWidth
                        validators={["required"]}
                        errorMessages={[t("Validation.this_field_is_required")]}
                      />
                    )}
                  />
                )}
              </Grid>

              <Grid item sm={12} xs={12}>
                {campaignTypeList && (
                  <Autocomplete
                    options={campaignTypeList}
                    defaultValue={campaignType}
                    disableCloseOnSelect
                    getOptionSelected={(option, value) =>
                      option.id === value.id
                    }
                    getOptionLabel={(option) => option.name}
                    onChange={(event, value) => {
                      this.setState({ campaignType: value });
                    }}
                    style={{ width: "100%" }}
                    renderInput={(params) => (
                      <TextValidator
                        {...params}
                        value={campaignType||""}
                        label={
                          <span>
                            <span style={{ color: "red" }}> * </span>
                            {t("Loại chiến dịch")}
                          </span>
                        }
                        fullWidth
                        validators={["required"]}
                        errorMessages={[t("Validation.this_field_is_required")]}
                      />
                    )}
                  />
                )}
              </Grid>
  
              <Grid item sm={4} xs={4}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("general.price")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="price"
                  value={price}
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={2} xs={2}>
                {currencyList && (
                  <Autocomplete
                    options={currencyList}
                    defaultValue={currencyPrice}
                    disableCloseOnSelect
                    getOptionSelected={(option, value) =>
                      option.id === value.id
                    }
                    getOptionLabel={(option) => option.name}
                    onChange={(event, value) => {
                      this.setState({ currencyPrice: value });
                    }}
                    style={{ width: "100%" }}
                    renderInput={(params) => (
                      <TextValidator
                        {...params}
                        value={currencyPrice||""}
                        label={
                          <span>
                            <span style={{ color: "red" }}> * </span>
                            {t("Tiền tệ")}
                          </span>
                        }
                        fullWidth
                        validators={["required"]}
                        errorMessages={[t("Validation.this_field_is_required")]}
                      />
                    )}
                  />
                )}
              </Grid>

              <Grid item sm={4} xs={4}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Payout")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="payout"
                  value={payout}
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>

              <Grid item sm={2} xs={2}>
                {currencyList && (
                  <Autocomplete
                    options={currencyList}
                    defaultValue={currencyPayout}
                    disableCloseOnSelect
                    getOptionSelected={(option, value) =>
                      option.id === value.id
                    }
                    getOptionLabel={(option) => option.name}
                    onChange={(event, value) => {
                      this.setState({ currencyPayout: value });
                    }}
                    style={{ width: "100%" }}
                    renderInput={(params) => (
                      <TextValidator
                        {...params}
                        value={currencyPayout||""}
                        label={
                          <span>
                            <span style={{ color: "red" }}> * </span>
                            {t("Tiền tệ")}
                          </span>
                        }
                        fullWidth
                        validators={["required"]}
                        errorMessages={[t("Validation.this_field_is_required")]}
                      />
                    )}
                  />
                )}
              </Grid>

              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Mô tả")}
                    </span>
                  }
                  multiline
                  rows={4}
                  rowsMax={8}
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

export default ProductDialog;
