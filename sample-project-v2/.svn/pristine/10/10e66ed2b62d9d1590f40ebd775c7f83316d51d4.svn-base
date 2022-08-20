import {
  Grid,
  TextField,
  IconButton,
  Icon,
  Button,
  TableHead,
  TableCell,
  TableRow,
  Checkbox,
  TablePagination,
  Tooltip,
  FormControl,
  Input,
  InputAdornment,
} from "@material-ui/core";
import { createMuiTheme } from "@material-ui/core/styles";
import React, { Component } from "react";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import MaterialTable, {
  MTableToolbar,
  Chip,
  MTableBody,
  MTableHeader,
} from "material-table";
import { useTranslation, withTranslation, Trans } from "react-i18next";

import {
  searchByPage,
  filterByPage,
  handleDeleteList,
  getFlatRootChild,
  getAllByRoot,
  deleteItem,
  saveItem,
  getItemById,
  getAllItem,
  deleteCheckItem,
} from "./ProductService";

import CampaignDialog from "../Campaign/CampaignDialog";
import AgencyDialog from "./ProductDialog";
import FilterForm from "./ProductFilterForm";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { Helmet } from "react-helmet";
import { withStyles } from "@material-ui/core/styles";
import SearchIcon from "@material-ui/icons/Search";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});
const LightTooltip = withStyles((theme) => ({
  tooltip: {
    backgroundColor: theme.palette.common.white,
    color: "rgba(0, 0, 0, 0.87)",
    boxShadow: theme.shadows[1],
    fontSize: 11,
    position: "absolute",
    top: "-15px",
    left: "-30px",
    width: "80px",
  },
}))(Tooltip);

function MaterialButton(props) {
  const { t, i18n } = useTranslation();
  const item = props.item;
  return (
    <div className="none_wrap">
      <LightTooltip
        title={t("general.editIcon")}
        placement="right-end"
        enterDelay={300}
        leaveDelay={200}
      >
        <IconButton size="small" onClick={() => props.onSelect(item, 0)}>
          <Icon fontSize="small" color="primary">
            edit
          </Icon>
        </IconButton>
      </LightTooltip>

      <LightTooltip
        title={t("general.deleteIcon")}
        placement="right-end"
        enterDelay={300}
        leaveDelay={200}
      >
        <IconButton size="small" onClick={() => props.onSelect(item, 1)}>
          <Icon fontSize="small" color="error">
            delete
          </Icon>
        </IconButton>
      </LightTooltip>

      <LightTooltip
        title={t("general.addCampaignIcon")}
        placement="right-end"
        enterDelay={300}
        leaveDelay={200}
      >
        <IconButton size="small" onClick={() => props.onSelect(item, 2)}>
          <Icon fontSize="small" color="primary">
            add
          </Icon>
        </IconButton>
      </LightTooltip>
    </div>
  );
}
class Category extends React.Component {
  state = {
    pageIndex: 1,
    pageSize: 10,
    sortBy: "createDate",
    sortDirection: "desc",
    keyword: "",
    nationaly: {},
    category: {},
    currencyPrice: {},
    currencyPayout: {},
    data: [],
    totalElements: 0,
    itemList: [],
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    shouldOpenConfirmationDeleteAllDialog: false,
    shouldOpenAddCampaignDialog: false,
    shouldOpenNotificationPopup: false,
    shouldOpenFilterForm: false,
    Notification: "",
  };
  constructor(props) {
    super(props);
    //this.state = {keyword: ''};
    this.handleTextChange = this.handleTextChange.bind(this);
  }
  handleTextChange(event) {
    this.setState({ keyword: event.target.value });
  }

  handleKeyDownEnterSearch = (e) => {
    if (e.key === "Enter") {
      this.search();
    }
  };
  componentDidMount() {
    this.updatePageData();
  }

  search() {
    this.setState({  pageIndex: 0 }, this.updatePageData);
  }

  updatePageData = () => {
    let { pageIndex, pageSize, sortBy, sortDirection, keyword, nationaly, category, currencyPrice, currencyPayout } = this.state;
    filterByPage({ pageIndex, pageSize, sortBy, sortDirection, keyword, nationaly, category, currencyPrice, currencyPayout }).then(res => {
      this.setState({ itemList: [...res.data.content], totalElements: res.data.totalElements })
    }).catch(console.log);
    // var searchObject = {};
    // searchObject.keyword = "";
    // searchObject.pageIndex = this.state.page + 1;
    // searchObject.pageSize = this.state.rowsPerPage;
    // searchByPage(searchObject).then(({ data }) => {
    //   this.setState({ itemList: [...data.content], totalElements: data.totalElements, })
    // });
  };
  
  setPage = (pageIndex) => {
    this.setState({ pageIndex }, function () {
      this.updatePageData();
    });
  };

  setRowsPerPage = (event) => {
    this.setState({ pageSize: event.target.value, pageIndex: 0 }, function () {
      this.updatePageData();
    });
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };

  handleOKEditClose = () => {
    this.setState(
      {
        shouldOpenEditorDialog: false,
        shouldOpenConfirmationDialog: false,
        shouldOpenAddCampaignDialog: false
      },
      () => {
        this.updatePageData();
      }
    );
  };

  handleDelete = (id) => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true,
    });
  };
  handleDialogClose = () => {
    this.setState(
      {
        shouldOpenEditorDialog: false,
        shouldOpenConfirmationDialog: false,
        shouldOpenConfirmationDeleteAllDialog: false,
        shouldOpenAddCampaignDialog: false,
        shouldOpenNotificationPopup: false,
        data: [],
      },
      () => {
        this.updatePageData();
      }
    );
  };

  handleOKEditClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenConfirmationDeleteAllDialog: false,
      shouldOpenAddCampaignDialog: false,
    });
    this.setPage(0);
  };

  handleDelete = (id) => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true,
    });
  };

  handleConfirmationResponse = () => {
    let { t } = this.props
    if (this.state.itemList.length === 1 && this.state.page === 1) {
      let count = this.state.page - 1;
      this.setState({
        page: count,
      })
    }
    deleteItem(this.state.id).then(() => {
      this.updatePageData();
      this.handleDialogClose();
      toast.success(t("Xóa thành công"));
    }).catch(() => {
      toast.warning(t("Không thể xóa danh mục này"));
    });
  };
  handleEditItem = (item) => {
    this.setState({
      item: item,
      shouldOpenEditorDialog: true,
    });
  };
  handleDeleteButtonClick = () => {
    let { t } = this.props
    if (!this.data || this.data.length === 0) {
      toast.warning(t("general.noti_check_data"));

    } else if (this.data.length === this.state.itemList.length) {
      this.setState({ shouldOpenConfirmationDeleteAllDialog: true });
    } else {
      this.setState({ shouldOpenConfirmationDeleteAllDialog: true });
    }
  };
  async handleDeleteList(list) {
    let listAlert = [];
    var { t } = this.props;
    for (var i = 0; i < list.length; i++) {
      try {
        await deleteItem(list[i].id);
      } catch (error) {
        listAlert.push(list[i].name);
      }
    }
    this.handleDialogClose();
    if (listAlert.length === list.length) {
      toast.warning(t("Danh mục đã được sử dụng"));
      // alert("Các trạng thái đều đã sử dụng");
    } else if (listAlert.length > 0) {
      toast.warning(t("Đã xoá các danh mục chưa sử dụng"));
      // alert("Đã xoá các trạng thái chưa sử dụng");
    }
  }
  handleDeleteAll = (event) => {
    this.handleDeleteList(this.data)
      .then(() => {
        this.updatePageData();
        toast.success("Xóa thành công");
        this.data = null;
      })
      .catch((err) => {
        console.log("loi");
      });
  };
  handleAddCampaignButtonClick = () => {
    this.setState({ shouldOpenAddCampaignDialog: true });
  }

  handleAddCampaignConfirmation = (item) => {
    this.setState({
      item: item,
      shouldOpenAddCampaignDialog: true
    })
  }

  handleToggleFilterForm = () => {
    this.setState({ 
      shouldOpenFilterForm: !this.state.shouldOpenFilterForm,
      nationaly: {},
      category: {},
      currencyPrice: {},
      currencyPayout: {},
    })
  }

  render() {
    const { t, i18n } = this.props;
    let { keyword, shouldOpenNotificationPopup } = this.state;
    let TitlePage = t("Danh mục sản phẩm");

    let columns = [
      {
        title: t("general.action"),
        field: "custom",
        align: "left",
        width: "120px",
        headerStyle: {
          padding: "0px",
        },
        cellStyle: {
          padding: "0px",
        },
        render: (rowData) => (
          <MaterialButton
            item={rowData}
            onSelect={(rowData, method) => {
              if (method === 0) {
                getItemById(rowData.id).then(({ data }) => {
                  if (data.parent === null) {
                    data.parent = {};
                  }
                  this.setState({
                    item: data,
                    shouldOpenEditorDialog: true,
                  });
                });
              } else if (method === 1) {
                this.handleDelete(rowData.id);
              } else if(method === 2) {
                getItemById(rowData.id).then(({ data }) => {
                  if (data.parent === null) {
                    data.parent = {};
                  }
                  this.setState({
                    product: data,
                    shouldOpenAddCampaignDialog: true,
                  });
                  console.log("ten", data.name);
                });
              } else {
                alert("Call Selected Here:" + rowData.id);
              }
            }}
          />
        ),
      },
      {
        title: t("Ảnh"), field: "custom",
        render: (rowData) => <img src={rowData.mainImageUrl} alt={`Ảnh của sản phẩm ${rowData.name}`} width="100px" />,
        width: "100",
        align: "center"
      },
      { title: t("general.name"), field: "name", align: "center" },
      { title: t("general.code"), field: "code", align: "center" },
      {
        title: t("Loại"), field: "custom",
        render: (rowData) => rowData.category.name,
        align: "center"
      },
      {
        title: t("Quốc gia"), field: "custom",
        render: (rowData) => rowData.nationaly.name,
        align: "center"
      },
      {
        title: t("general.price"), field: "custom",
        render: (rowData) => (""+rowData.price).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.") + rowData.currencyPrice.currencySymbols,
        align: "center"
      },
      {
        title: t("Payout"), field: "custom",
        render: (rowData) => (""+rowData.payout).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.") + rowData.currencyPayout.currencySymbols,
        align: "center"
      },
      // { title: t("Mô tả"), field: "description", align: "center" },
      { title: t("Mô tả"), field: "description", align: "center",
        render: (rowData) => (
          <LightTooltip
            title={rowData.description}
            placement="left-start"
            enterDelay={300}
            leaveDelay={200}
          >
            <IconButton size="small">
              <Icon fontSize="small" color="primary">
                info
              </Icon>
            </IconButton>
          </LightTooltip>
        )
      },
    ];

    console.log(this.state.itemList);
    return (
      <div className="m-sm-30">

        <Grid container spacing={2} justify="space-between">
          <Grid item md={4} xs={12}>
            <Button
              className="align-bottom mr-16 mb-16"
              variant="contained"
              color="primary"
              onClick={() => this.handleEditItem(null)}
            >
              {t("general.add")}
            </Button>
            <Button
              className="align-bottom mr-16 mb-16"
              variant="contained"
              color="primary"
              onClick={this.handleDeleteButtonClick}
            >
              {t("general.delete")}
            </Button>
            <Button
              className="align-bottom mb-16"
              variant="contained"
              color="primary"
              onClick={this.handleToggleFilterForm}
            >
              {t(`${this.state.shouldOpenFilterForm ? 'Ẩn' : 'Hiện'} lọc`)}
            </Button>

            {this.state.shouldOpenConfirmationDeleteAllDialog && (
              <ConfirmationDialog
                open={this.state.shouldOpenConfirmationDeleteAllDialog}
                onConfirmDialogClose={this.handleDialogClose}
                onYesClick={this.handleDeleteAll}
                title={t("confirm")}
                text={t('DeleteAllConfirm')}
                Yes={t('general.Yes')}
                No={t('general.No')}
              />
            )}

          </Grid>
          <Grid item md={4} sm={12} xs={12}>
            <FormControl fullWidth style={{ marginTop: "6px" }}>
              <Input
                className="search_box w-100"
                onChange={this.handleTextChange}
                onKeyDown={this.handleKeyDownEnterSearch}
                placeholder={t("general.enterKeyword")}
                id="search_box"
                startAdornment={
                  <InputAdornment>
                    <Link>
                      {" "}
                      <SearchIcon
                        onClick={() => this.search()}
                        style={{ position: "absolute", top: "0", right: "0" }}
                      />
                    </Link>
                  </InputAdornment>
                }
              />
            </FormControl>
          </Grid>
          <Grid item xs={12}>
            <FilterForm
              filter={this.state}
              changeCategory={category => this.setState({ category })}
              changeNationaly={nationaly => this.setState({ nationaly })}
              changeCurrencyPrice={currencyPrice => this.setState({ currencyPrice })}
              changeCurrencyPayout={currencyPayout => this.setState({ currencyPayout })}
              updatePageData={() => {
                this.setState({ pageIndex: 0 }, this.updatePageData);
              }}
              t={t}
            />
          </Grid>
          <Grid item xs={12}>
            <div>
              {this.state.shouldOpenEditorDialog && (
                <AgencyDialog
                  t={t}
                  i18n={i18n}
                  handleClose={this.handleDialogClose}
                  open={this.state.shouldOpenEditorDialog}
                  handleOKEditClose={this.handleOKEditClose}
                  item={this.state.item}
                />
              )}

              {this.state.shouldOpenConfirmationDialog && (
                <ConfirmationDialog
                  title={t("general.confirm")}
                  open={this.state.shouldOpenConfirmationDialog}
                  onConfirmDialogClose={this.handleDialogClose}
                  onYesClick={this.handleConfirmationResponse}
                  title={t("confirm")}
                  text={t('DeleteConfirm')}
                  Yes={t('general.Yes')}
                  No={t('general.No')}
                />
              )}


              {this.state.shouldOpenAddCampaignDialog && (
                <CampaignDialog
                  t={t}
                  i18n={i18n}
                  handleClose={this.handleDialogClose}
                  open={this.state.shouldOpenAddCampaignDialog}
                  handleOKEditClose={this.handleOKEditClose}
                  product={this.state.product}
                />
              )}


            </div>
            <MaterialTable
              title={t("general.list")}
              data={this.state.itemList}
              columns={columns}
              //parentChildData={(row, rows) => rows.find(a => a.id === row.parentId)}
              parentChildData={(row, rows) => {
                var list = rows.find((a) => a.id === row.parentId);
                return list;
              }}
              localization={{
                body: {
                  emptyDataSourceMessage: `${t(
                    "general.emptyDataMessageTable"
                  )}`,
                },
                toolbar: {
                  // nRowsSelected: `${t('general.selects')}`,
                  nRowsSelected: `${t("general.selects")}`,
                },
              }}
              options={{
                selection: true,
                actionsColumnIndex: -1,
                paging: false,
                search: false,
                rowStyle: (rowData) => ({
                  backgroundColor:
                    rowData.tableData.id % 2 === 1 ? "#EEE" : "#FFF",
                }),
                maxBodyHeight: "1000px",
                minBodyHeight: "370px",
                headerStyle: {
                  backgroundColor: "#358600",
                  color: "#fff",
                },
                padding: "dense",
                toolbar: false,
              }}
              components={{
                Toolbar: (props) => <MTableToolbar {...props} />,
              }}
              onSelectionChange={(rows) => {
                this.data = rows;
              }}
            // actions={[
            //   {
            //     tooltip: 'Remove All Selected Users',
            //     icon: 'delete',
            //     onClick: (evt, data) => {
            //       this.handleDeleteAll(data);
            //       console.log(data)
            //       alert('You want to delete ' + data.length + ' rows');
            //     }
            //   },
            // ]}
            />
            <TablePagination
              align="left"
              className="px-16"
              rowsPerPageOptions={[1, 2, 3, 5, 10, 25]}
              component="div"
              labelRowsPerPage={t("general.rows_per_page")}
              labelDisplayedRows={({ from, to, count }) =>
                `${from}-${to} ${t("general.of")} ${count !== -1 ? count : `more than ${to}`
                }`
              }
              count={this.state.totalElements}
              rowsPerPage={this.state.pageSize}
              page={this.state.pageIndex}
              backIconButtonProps={{
                "aria-label": "Previous Page",
              }}
              nextIconButtonProps={{
                "aria-label": "Next Page",
              }}
              onChangePage={this.handleChangePage}
              onChangeRowsPerPage={this.setRowsPerPage}
            />
          </Grid>
        </Grid>
      </div>
    );
  }
}
export default Category;
