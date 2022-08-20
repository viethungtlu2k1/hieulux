import {
    InputAdornment,
    Input,
    Grid,
    MuiThemeProvider,
    TextField,
    Button,
    TableHead,
    TableCell,
    TableRow,
    Checkbox,
    TablePagination,
    Radio,
    Dialog
  } from "@material-ui/core";
  import { createMuiTheme } from "@material-ui/core/styles";
  import React, { Component } from "react";
  import ReactDOM from "react-dom";
  import MaterialTable, {
    MTableToolbar,
    Chip,
    MTableBody,
    MTableHeader
  } from "material-table";
  import { useTranslation, withTranslation, Trans } from "react-i18next";
 
  import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
  import DialogActions from "@material-ui/core/DialogActions";
  import DialogContent from "@material-ui/core/DialogContent";
  import DialogTitle from "@material-ui/core/DialogTitle";
  import Draggable from "react-draggable";
  import Paper from "@material-ui/core/Paper";
  import { Link } from "react-router-dom";
  import SearchIcon from "@material-ui/icons/Search";
  
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
  class MultipleProduct extends React.Component {
    constructor(props) {
      super(props);
      this.handleKeyWordChange = this.handleKeyWordChange.bind(this);
    }
    state = {
      rowsPerPage: 1000,
      page: 0,
      totalElements: 0,
      itemList: [],
      data: [],
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      selectedItemList: [],
      keyword: ""
    };
    setPage = page => {
      this.setState({ page }, function() {
        this.updatePageData();
      });
    };
  
    setRowsPerPage = event => {
      this.setState({ rowsPerPage: event.target.value, page: 0 }, function() {
        this.updatePageData();
      });
    };
  
    handleChangePage = (event, newPage) => {
      this.setPage(newPage);
    };
  
    handleCheckboxChange = rowData => {
      let selectedItemList = this.state.selectedItemList;
      if (selectedItemList.some(selected => selected.product.id === rowData.id)) {
        selectedItemList = selectedItemList.filter(
          selected => selected.product.id !== rowData.id
        );
      } else {
        let p = {}
        p.product = rowData;
        selectedItemList.push(p);
      }
      this.setState({
        selectedItemList
      });
    };
  
    componentDidMount() {
      let selectedItemList = []
      if(this.props.productInventoryReceivingVoucher != null){
       selectedItemList = this.props.productInventoryReceivingVoucher.slice();
      }
      this.setState({ selectedItemList:selectedItemList });
      this.updatePageData();
    }
  
    updatePageData = () => {
      
    };
  
    handleKeyDownEnterSearch = e => {
      if (e.key === "Enter") {
        this.search();
      }
    };
  
    search = () => {
      var searchObject = {};
      let { keyword, } = this.state;
      if (keyword != null) {
        searchObject.text = keyword.trim();
      }
      searchObject.pageIndex = this.state.page;
      searchObject.pageSize = this.state.rowsPerPage;
      
    };
  
    selectedAllItem = () => {
      var searchObject = {};
      let { keyword, } = this.state;
      if (keyword != null) {
        searchObject.text = keyword.trim();
      }
      searchObject.pageIndex = 1
      searchObject.pageSize = 999999;
      
    }
  
    handleKeyWordChange(event) {
      this.setState({ keyword: event.target.value });
    }
  
    selectedAllCheck = () => {
      let result = true;
      for (let item of this.state.itemList) {
        if (
          !this.state.selectedItemList.some(selected => selected.id === item.id)
        ) {
          result = false;
        }
      }
      return result;
    };
  
    handleSelectAll = () => {
      let selectedItemList = this.state.selectedItemList;
      let isCheckedAll = this.selectedAllCheck();
      if (isCheckedAll) {
        let removeIDList = this.state.itemList.map(item => item.id);
        selectedItemList = selectedItemList.filter(
          selected => !removeIDList.includes(selected.id)
        );
      } else {
        for (let item of this.state.itemList) {
          if (!selectedItemList.some(selected => selected.id === item.id)) {
            selectedItemList.push(item);
          }
        }
      }
      this.setState({
        selectedItemList
      });
    };
  
    handleDialogClose = () => {
      this.setState(
        {
          selectedItemList: []
        },
        () => console.log(this.state.selectedItemList)
      );
      this.props.handleClose();
    };
  
    render() {
      const { t, i18n, handleClose, handleSelect, open } = this.props;
      let { keyword } = this.state;
      let columns = [
        {
          // title: (
          //   <Checkbox
          //     checked={this.selectedAllCheck()}
          //     onChange={this.handleSelectAll}
          //   />
          // ),
          field: "custom",
          align: "left",
          width: "150",
          render: rowData => (
            <Checkbox
              checked={this.state.selectedItemList.some(
                selected => selected.product.id === rowData.id
              )}
              onChange={() => this.handleCheckboxChange(rowData)}
            />
          )
        },
        { title: t("general.name"), field: "name", width: "150" },
        { title: t("general.code"), field: "code", align: "left", width: "150" },
        { title: t("Số lượng hiện có"), field: "soLuongDangCo", align: "left", width: "150" }
      ];
      return ( 
         <Dialog 
          // style={
          //   {position:"absolute"},
          //   {left: "500px"},
            
          //   {width: "45%"}
            
            
          // }
          onClose={handleClose}
          open={open}
          PaperComponent={PaperComponent}
          maxWidth={"md"}
          fullWidth={true}
        >
          <DialogTitle style={{ cursor: "move" }} id="draggable-dialog-title">
            <h4 className="mb-20">{t("Danh sách sản phẩm")}</h4>
          </DialogTitle>
  
          <DialogContent  dividers>
            <Grid className="mb-16" container spacing={2}>
              <Grid item md={12} sm={12} xs={12} style={{ marginLeft: "30%" }}>
                <Grid item md={12} sm={12} xs={12}>
                  <Input
                    label={t("EnterSearch")}
                    type="text"
                    name="keyword"
                    value={keyword}
                    onChange={this.handleKeyWordChange}
                    onKeyDown={this.handleKeyDownEnterSearch}
                    className="w-100 mb-16 mr-10 ml-10"
                    id="search_box"
                    placeholder={t("general.enterSearch")}
                    startAdornment={
                      <InputAdornment>
                        <Link to="#">
                          {" "}
                          <SearchIcon
                            onClick={() => this.search()}
                            style={{
                              position: "absolute",
                              top: "0",
                              right: "0"
                            }}
                          />
                        </Link>
                      </InputAdornment>
                    }
                  />
                </Grid>
              </Grid>
              <Grid  item md={12} sm={12} xs={12}>
                <MaterialTable 
                  data={this.state.itemList}
                  columns={columns}
                //   parentChildData={(row, rows) => {
                //     var list = rows.find(a => a.id === row.parentId);
                //     return list;
                //   }}
                  options={{
                  selection: false,
                  actionsColumnIndex: -1,
                  paging: false,
                  search: false,
                  rowStyle: rowData => ({
                  backgroundColor: (rowData.tableData.id % 2 === 1) ? '#EEE' : '#FFF',
                  }), 
                  headerStyle: {
                  backgroundColor: "#358600",
                  color:'#fff',
                  },
                  padding: 'dense',
                  toolbar: false
                  }}
                  components={{
                    Toolbar: props => (
                      <div style={{ witdth: "100%" }}>
                        <MTableToolbar {...props} />
                      </div>
                    )
                  }}
                />
                {/* <TablePagination
                  align="left"
                  className="px-16"
                  rowsPerPageOptions={[5, 10, 25]}
                  component="div"
                  labelRowsPerPage={t('general.rows_per_page')}
                  labelDisplayedRows={ ({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
                  count={this.state.totalElements}
                  rowsPerPage={this.state.rowsPerPage}
                  page={this.state.page}
                  backIconButtonProps={{
                    "aria-label": "Previous Page"
                  }}
                  nextIconButtonProps={{
                    "aria-label": "Next Page"
                  }}
                  onChangePage={this.handleChangePage}
                  onChangeRowsPerPage={this.setRowsPerPage}
                /> */}
              </Grid>
            </Grid>
          </DialogContent>
  
          <DialogActions>
            <Button
              className="mr-16 align-bottom"
              variant="contained"
              color="secondary"
              onClick={this.handleDialogClose}
            >
              {t("general.cancel")}
            </Button>
            
            <Button
              className="mr-16 align-bottom"
              variant="contained"
              color="primary"
              onClick={() => handleSelect(this.state.selectedItemList)}
            >
              {t("general.select")}
            </Button>
          </DialogActions>
        </Dialog>
      );
    }
  }
  export default MultipleProduct;
  