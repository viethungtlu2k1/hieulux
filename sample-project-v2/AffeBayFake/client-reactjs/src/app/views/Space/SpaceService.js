import axios from "axios";
import ConstantList from "../../appConfig";

const API_PATH = ConstantList.API_ENPOINT + "/api/space/";
const API_PATH_NATIONALY = ConstantList.API_ENPOINT + "/api/nationaly/";
const API_PATH_CATEGORY = ConstantList.API_ENPOINT + "/api/category/";


export const searchByPage = (searchObject) => {
  return axios.post(API_PATH + "searchByPage", searchObject);
};

export const deleteItem = id => {
  return axios.delete(API_PATH + id);
};

export const saveItem = item => {
  return axios.post(API_PATH, item);
};
export const updateItem = item => {
  return axios.put(API_PATH + item.id, item);
};

export const getItemById = id => {
  return axios.get(API_PATH + id);
};

export const checkName = (id, name) => {
  const config = { params: { id: id, name: name } };
  return axios.get(API_PATH + "checkName", config);
};

export const getDetailWithNationaly = (id) => {
  return axios.get(API_PATH + "nationaly/" + id)
}
export const getDetailWithCategory = (id) => {
  return axios.get(API_PATH + "category/" + id)
}

export const getAllNational = () => {
  return axios.get(API_PATH_NATIONALY + "getAllNation")
}

export const getAllCategory = () => {
  return axios.get(API_PATH_CATEGORY + "getAllCategory")
}