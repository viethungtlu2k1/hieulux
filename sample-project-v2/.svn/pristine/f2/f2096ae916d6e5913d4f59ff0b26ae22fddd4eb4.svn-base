import React from 'react';
import { Grid, Button } from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { toast } from "react-toastify";
import {
  getAllCategory,
  getAllNationaly,
  getAllCurrency,
  getAllCampaignType,
} from "./ProductService";

class ProductFilterForm extends React.Component {
  state = {
    categoryList: [],
    nationalyList: [],
    currencyList: [],
    campaignTypeList: [],
    category: { name: 'abc'},
    defaultOption: {
      name: 'Tất cả',
      currencySymbols: 'Tất cả'
    }
  }
  componentDidMount() {
    Promise.all([
      getAllCategory(),
      getAllNationaly(),
      getAllCurrency()
    ]).then(([category, nationaly, currency]) => {
      this.setState({
        categoryList: [this.state.defaultOption, ...category.data.content],
        nationalyList: [this.state.defaultOption, ...nationaly.data.content],
        currencyList: [this.state.defaultOption, ...currency.data.content],
      });
    }).catch(err => {
      toast.warning(err + "");
    });
  }
  render() {
    let {
      categoryList,
      nationalyList,
      currencyList,
      defaultOption
    } = this.state;
    let {
      changeCategory,
      changeNationaly,
      changeCurrencyPrice,
      changeCurrencyPayout,
      updatePageData,
      t
    } = this.props;
    console.log('filter', this.props.filter)
    if (!this.props.filter.shouldOpenFilterForm) return (null);
    return(
      <ValidatorForm ref="form" onSubmit={updatePageData}>
          <Grid className="" container spacing={2}>
            <Grid item sm={3} xs={3}>
              {categoryList && (
                <Autocomplete
                  options={categoryList}
                  defaultValue={defaultOption}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  getOptionLabel={(option) => option.name}
                  onChange={(event, value) => {
                    changeCategory(value);
                  }}
                  disableClearable
                  style={{ width: "100%" }}
                  renderInput={(params) => (
                    <TextValidator
                      {...params}
                      value={this.props.filter.category||""}
                      label={
                        <span>{t("Loại sản phẩm")}</span>
                      }
                      fullWidth
                      validators={["required"]}
                      errorMessages={[t("Validation.this_field_is_required")]}
                    />
                  )}
                />
              )}
            </Grid>
            <Grid item sm={3} xs={3}>
              {nationalyList && (
                <Autocomplete
                  options={nationalyList}
                  defaultValue={defaultOption}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  getOptionLabel={(option) => option.name}
                  onChange={(event, value) => {
                    changeNationaly(value);
                  }}
                  disableClearable
                  style={{ width: "100%" }}
                  renderInput={(params) => (
                    <TextValidator
                      {...params}
                      value={this.props.filter.nationaly||""}
                      label={
                        <span>{t("Quốc gia")}</span>
                      }
                      fullWidth
                      validators={["required"]}
                      errorMessages={[t("Validation.this_field_is_required")]}
                    />
                  )}
                />
              )}
            </Grid>
            <Grid item sm={3} xs={3}>
              {currencyList && (
                <Autocomplete
                  options={currencyList}
                  defaultValue={defaultOption}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  getOptionLabel={(option) => option.currencySymbols}
                  onChange={(event, value) => {
                    changeCurrencyPrice(value);
                  }}
                  disableClearable
                  style={{ width: "100%" }}
                  renderInput={(params) => (
                    <TextValidator
                      {...params}
                      value={this.props.filter.currencyPrice||""}
                      label={
                        <span>{t("Tiền tệ")}</span>
                      }
                      fullWidth
                      validators={["required"]}
                      errorMessages={[t("Validation.this_field_is_required")]}
                    />
                  )}
                />
              )}
            </Grid>
            <Grid item sm={3} xs={3}>
              {currencyList && (
                <Autocomplete
                  options={currencyList}
                  defaultValue={defaultOption}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  getOptionLabel={(option) => option.currencySymbols}
                  onChange={(event, value) => {
                    changeCurrencyPayout(value);
                  }}
                  disableClearable
                  style={{ width: "100%" }}
                  renderInput={(params) => (
                    <TextValidator
                      {...params}
                      value={this.props.filter.currencyPayout||""}
                      label={
                        <span>{t("Tiền tệ (Payout)")}</span>
                      }
                      fullWidth
                      validators={["required"]}
                      errorMessages={[t("Validation.this_field_is_required")]}
                    />
                  )}
                />
              )}
            </Grid>
          </Grid>
          <div className="flex flex-space-between flex-middle mt-12">
            <Button
              variant="contained"
              style={{ marginRight: "15px" }}
              color="primary"
              type="submit"
            >
              {t("Lọc sản phẩm")}
            </Button>
          </div>
      </ValidatorForm>
    );
  }
}

export default ProductFilterForm;
