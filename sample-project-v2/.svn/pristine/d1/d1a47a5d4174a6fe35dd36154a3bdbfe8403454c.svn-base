import React, { createContext, useReducer } from 'react';
const EmployeeContext = createContext();

const constant = {
  FETCH_PRODUCT_REQUEST: 'FETCH_PRODUCT_REQUEST',
  FETCH_PRODUCT_SUCCESS: 'FETCH_PRODUCT_SUCCESS',
  FETCH_PRODUCT_FAILURE: 'FETCH_PRODUCT_FAILURE',
  UPDATE_PRODUCT: 'UPDATE_PRODUCT',
  SET_PAGE: 'SET_PAGE',
  SET_RPP: 'SET_RPP',
  OPEN_DELETE_DIALOG: 'OPEN_DELETE_DIALOG',
  CLOSE_DELETE_DIALOG: 'CLOSE_DELETE_DIALOG',
  OPEN_DELETE_ALL_DIALOG: 'OPEN_DELETE_ALL_DIALOG',
  CLOSE_DELETE_ALL_DIALOG: 'CLOSE_DELETE_ALL_DIALOG',
  SET_SELECTION: 'SET_SELECTION',
  SET_SEARCH_TEXT: 'SET_SEARCH_TEXT',
}

const reducer = (state, action) => {
  switch (action.type) {
    case constant.FETCH_PRODUCT_REQUEST:
      return {...state,  isLoading: true, isError: false }
    case constant.FETCH_PRODUCT_SUCCESS:
      return {
        ...state, 
        payload: action.payload.PRODUCT, 
        isLoading: false, 
        isError: false,
        totalItem: action.payload.totalItem,
      }
    case constant.FETCH_PRODUCT_FAILURE:
      return {...state, isLoading: false, isError: true }
    case constant.UPDATE_PRODUCT:
      return {
        ...state,
        payload: state.payload.map(emp => {
          if (emp.id === action.payload.PRODUCT.id) {
            return action.payload.PRODUCT;
          }
          return emp;
        })
      }
    case constant.SET_PAGE:
      return { ...state, page: action.payload.page }
    case constant.SET_RPP:
      return { ...state, rpp: action.payload.rpp }
    case constant.OPEN_DELETE_DIALOG:
      return { ...state, showDialog: {
        ...state.showDialog, delete: action.payload.id
      } }
    case constant.CLOSE_DELETE_DIALOG:
      return { ...state, showDialog: {
        ...state.showDialog, delete: false
      } }
    case constant.OPEN_DELETE_ALL_DIALOG:
      return { ...state, showDialog: {
        ...state.showDialog, deleteAll: true
      } }
    case constant.CLOSE_DELETE_ALL_DIALOG:
      return { ...state, showDialog: {
        ...state.showDialog, deleteAll: false
      } }
    case constant.SET_SELECTION:
      return { ...state, selectionItem: action.payload.selection }
    case constant.SET_SEARCH_TEXT:
      return { ...state, search: action.payload.search }
    default:
      return state;
  }
}

const action = dispatch => {
  return {
    fetchProductRequest() {
      dispatch({ type: constant.FETCH_PRODUCT_REQUEST });
    },
    fetchProductSuccess(product, totalPage, totalItem) {
      dispatch({ type: constant.FETCH_PRODUCT_SUCCESS, payload: {
        product, totalPage, totalItem
      } });
    },
    updateProduct(product) {
      dispatch({ type: constant.UPDATE_PRODUCT, payload: {
        product
      } });
    },
    setPage: function(page) {
      dispatch({ type: constant.SET_PAGE, payload: { page } });
    },
    setRpp: function(rpp) {
      dispatch({ type: constant.SET_RPP, payload: { rpp } });
    },
    openDeleteDialog: function(id) {
      dispatch({ type: constant.OPEN_DELETE_DIALOG, payload: { id } });
    },
    closeDeleteDialog: function() {
      dispatch({ type: constant.CLOSE_DELETE_DIALOG });
    },
    openDeleteAllDialog: function() {
      dispatch({ type: constant.OPEN_DELETE_ALL_DIALOG });
    },
    closeDeleteAllDialog: function() {
      dispatch({ type: constant.CLOSE_DELETE_ALL_DIALOG });
    },
    setSelection: function(selection) {
      dispatch({ type: constant.SET_SELECTION, payload: { selection } });
    },
    setSearchText: function(search) {
      dispatch({ type: constant.SET_SEARCH_TEXT, payload: { search } });
    },
  }
}

const initialState = { 
  payload: [], 
  isLoading: false, 
  isError: false,
  totalItem: 0, 
  rpp: 5, 
  page: 1,
  search: '',
  showDialog: {
    delete: false,
    deleteAll: false
  },
  selectionItem: []
}

function EmployeeProvider(props) {
  const [state, dispatch] = useReducer(reducer, initialState);
  const setState = action(dispatch);
  return(
    <EmployeeContext.Provider value={[state, setState]}>
      {props.children}
    </EmployeeContext.Provider>
  );
}

export { EmployeeProvider, EmployeeContext };
