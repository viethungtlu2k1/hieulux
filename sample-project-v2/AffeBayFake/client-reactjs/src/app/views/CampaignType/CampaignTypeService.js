import axios from "axios";
import ConstantList from "../../appConfig";

const API_PATH = ConstantList.API_ENPOINT + "/api/campaignType/";

export const getTreeView = () => {
  var url = API_PATH + "tree/1/10000000";
  return axios.get(url);
};

export const getByPage = (page, pageSize) => {
  var pageIndex = page + 1;
  var params = pageIndex + "/" + pageSize;
  var url = API_PATH + params;
  return axios.get(url);
};

export const searchByPage = (searchObject) => {
  return axios.post(API_PATH +  "searchByPage", searchObject);
};

export const deleteItem = id => {
  return axios.delete(API_PATH + id);
};

export const saveItem = item => {
  return axios.post(API_PATH, item);
};
export const updateItem = item => {
  return axios.put(API_PATH +item.id, item);
};

export const getItemById = id => {
  return axios.get(API_PATH + id);
};

export const checkCode = (id, code) => {
  const config = { params: {id: id, code: code } };
  return axios.get(API_PATH + "checkCode", config);
};