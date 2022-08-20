import {
  Grid,
  Button,
  TablePagination,
  Radio,
  Dialog,
  DialogActions,
  InputAdornment, Input, DialogContent
} from '@material-ui/core'
import SearchIcon from '@material-ui/icons/Search';
import { Link } from "react-router-dom";
import React, { Component } from 'react'
import MaterialTable, {
  MTableToolbar
} from 'material-table'
import DialogTitle from '@material-ui/core/DialogTitle'
import Paper from '@material-ui/core/Paper'
import { searchByPage as getAllStaff, getItemById } from "../Staff/StaffService";
import Draggable from 'react-draggable';
function PaperComponent(props) {
  return (
    <Draggable
      handle="#draggable-dialog-title"
      cancel={'[class*="MuiDialogContent-root"]'}
    >
      <Paper {...props} />
    </Draggable>
  )
}
class SelectDepartmentPopup extends React.Component {
  constructor(props) {
    super(props)
    this.handleChange = this.handleChange.bind(this)
  }
  state = {
    keyword: "",
    rowsPerPage: 5,
    page: 0,
    data: [],
    totalElements: 0,
    itemList: [],
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    selectedItem: {},
    checkPermissionUserDepartment: true
  }

  setPage = (page) => {
    this.setState({ page }, function () {
      this.updatePageData();
    })
  }

  setRowsPerPage = (event) => {
    this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
      this.updatePageData();
    })
  }

  handleChangePage = (event, newPage) => {
    this.setPage(newPage)
  }

  updatePageData = () => {
    var searchObject = {}
    searchObject.keyword = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1
    searchObject.pageSize = this.state.rowsPerPage
    getAllStaff(searchObject).then(({ data }) => {
      this.setState({
        itemList: [...data.content],
        totalElements: data.totalElements,
      })
    })
  }

  handleClick = (event, item) => {
    if (item.id != null) {
      this.setState({ selectedValue: item.id, selectedItem: item })
    } else {
      this.setState({ selectedValue: null, selectedItem: null })
    }
  }

  componentWillMount() {
    let { open, handleClose, selectedItem } = this.props;
    this.setState({ selectedValue: selectedItem.id }, function () {
      this.updatePageData();
    })
  }

  search() {
    this.setPage(0, function () {
      var searchObject = {};
      searchObject.keyword = this.state.keyword;
      searchObject.pageIndex = this.state.page;
      searchObject.pageSize = this.state.rowsPerPage;
      getAllStaff(searchObject).then(({ data }) => {
        this.setState({
          itemList: [...data.content],
          totalElements: data.totalElements,
        })
      })
    })
  }
  onClickRow = (selectedRow) => {
    document.querySelector(`#radio${selectedRow.id}`).click();
  }

  handleChange(event) {
    this.setState({ keyword: event.target.value })
  }

  handleKeyDownEnterSearch = e => {

    if (e.key === 'Enter') {
      this.setState({ keyword: e.target.value }, () => {
        this.search();
      })
      console.log("keyword: " + this.state.keyword)
    }
  };

  render() {
    const {
      t,
      i18n,
      handleClose,
      handleSelect,
      selectedItem,
      open,
    } = this.props
    let { keyword } = this.state
    let columns = [
      {
        title: t('general.select'),
        field: 'custom',
        align: 'left',
        width: '250',
        cellStyle: {
          padding: '0px',
          paddingLeft: '10px',
        },
        render: (rowData) => (
          <Radio
            id={`radio${rowData.id}`}
            name="radSelected"
            value={rowData.id}
            checked={this.state.selectedValue === rowData.id}
            onClick={(event) => this.handleClick(event, rowData)}
          />
        ),
      },
      {
        title: t('general.name'), field: '', align: 'left', width: '150',
        render: (rowData) => (<span>{rowData.name != "" && rowData.name != null ? rowData.name : ""}</span>)
      }
    ]
    return (
      <Dialog
        onClose={handleClose}
        open={open}
        PaperComponent={PaperComponent}
        maxWidth={'md'}
        fullWidth
      >
        <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
          <span className="mb-20">{t('menu.human_resources')}</span>
        </DialogTitle>
        <DialogContent style={{ height: '370px' }}>
          <Grid item md={6} sm={12} xs={12}>
            <Input
              className="w-100 mb-16"
              //label={t('Department.search')}
              name="keyword"
              value={keyword}
              onChange={this.handleChange}
              onKeyDown={this.handleKeyDownEnterSearch}
              id="search_box"
              placeholder={t("maintainRequestStatus.filter")}
              startAdornment={
                <InputAdornment >
                  <Link> <SearchIcon
                    onClick={() => this.search(keyword)}
                    style={{
                      position: "absolute",
                      top: "0",
                      right: "0"
                    }} /></Link>
                </InputAdornment>
              }
            />
          </Grid>
          {/* Show table staff */}
          <Grid item xs={12}>
            <MaterialTable
              title={t('general.list')}
              data={this.state.itemList}
              columns={columns}
              //parentChildData={(row, rows) => rows.find(a => a.id === row.parentId)}

              localization={{
                body: {
                  emptyDataSourceMessage: `${t('general.emptyDataMessageTable')}`
                },
                toolbar: {
                  nRowsSelected: `${t('general.selects')}`
                }
              }}
              onRowClick={((evt, selectedRow) => this.onClickRow(selectedRow))}

              options={{
                toolbar: false,
                selection: false,
                actionsColumnIndex: -1,
                paging: false,
                search: false,
                padding: 'dense'
              }}
              components={{
                Toolbar: (props) => (
                  <div style={{ witdth: '100%' }}>
                    <MTableToolbar {...props} />
                  </div>
                ),
              }}
              onSelectionChange={(rows) => {
                this.data = rows
              }}
            />
            <TablePagination
              align="left"
              className="px-16"
              rowsPerPageOptions={[1, 2, 3, 5, 10, 25]}
              component="div"
              labelRowsPerPage={t('general.rows_per_page')}
              labelDisplayedRows={({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
              count={this.state.totalElements}
              rowsPerPage={this.state.rowsPerPage}
              page={this.state.page}
              backIconButtonProps={{
                'aria-label': 'Previous Page',
              }}
              nextIconButtonProps={{
                'aria-label': 'Next Page',
              }}
              onChangePage={this.handleChangePage}
              onChangeRowsPerPage={this.setRowsPerPage}
            />
          </Grid>
        </DialogContent>
        <DialogActions>
          <Button
            className="mb-16 mr-12 align-bottom"
            variant="contained"
            color="secondary"
            onClick={() => handleClose()}
          >
            {t('general.cancel')}
          </Button>
          <Button
            className="mb-16 mr-16 align-bottom"
            variant="contained"
            color="primary"
            onClick={() => handleSelect(this.state.selectedItem)}
          >
            {t('general.select')}
          </Button>
        </DialogActions>
      </Dialog>
    )
  }
}
export default SelectDepartmentPopup
