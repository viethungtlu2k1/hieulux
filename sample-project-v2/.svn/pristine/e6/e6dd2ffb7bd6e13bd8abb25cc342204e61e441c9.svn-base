import axios from 'axios';
import ConstantList from "../../appConfig";

const API_PATH = ConstantList.API_ENPOINT + "/api/product/";
const API_PATH_Category = ConstantList.API_ENPOINT + "/api/category";
const API_PATH_Nationaly = ConstantList.API_ENPOINT + "/api/nationaly";
const API_PATH_Currency = ConstantList.API_ENPOINT + "/api/currency";
const API_PATH_CampaignType = ConstantList.API_ENPOINT + "/api/campaignType";

function toQueryString(obj) {
  var str = [];
  for (var p in obj)
    if (obj.hasOwnProperty(p)) {
      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    }
  return str.join("&");
}

export function fetchProductByPage(params = {}, cancelToken = '') {
  return new Promise(async function(resolve, reject) {
    try {
      const result = await axios.get(API_PATH+'search?'+toQueryString(params), {
        cancelToken
      });
      resolve(result);
    } catch (e) {
      reject(false);
    }
  })
}

export function searchProductByPage(searchObject, cancelToken = '') {
  return new Promise(async function(resolve, reject) {
    try {
      const result = await axios.get(API_PATH+'searchByPage', searchObject, {
        cancelToken
      });
      resolve(result);
    } catch (e) {
      reject(false);
    }
  })
}

export function createProduct(newProduct) {
  return new Promise(async function(resolve, reject) {
    try {
      const results = await axios.post(API_PATH, newProduct, {
        headers: {
          "Content-Type": 'application/json'
        }
      });
      resolve(results);
    } catch (error) {
      reject(false);
    }
  })
}

export function updateProduct(productId, product) {
  return new Promise(async function(resolve, reject) {
    try {
      const results = await axios.put(API_PATH+productId, product, {
        headers: {
          "Content-Type": 'application/json'
        }
      });
      resolve(results);
    } catch (error) {
      reject(false);
    }
  })
}

export function deleteProduct(productId) {
  return new Promise(async function(resolve, reject) {
    try {
      const results = await axios.delete(API_PATH+productId);
      resolve(results);
    } catch (error) {
      console.log(error)
      reject(false);
    }
  })
}

export function deleteManyProduct(listProductId) {
  if (typeof listProductId == 'array') {
    listProductId = listProductId.join(',');
  }
  return new Promise(async function(resolve, reject) {
    try {
      const results = await axios.delete(API_PATH+'?id='+listProductId.join(','));
      resolve(results);
    } catch (error) {
      reject(false);
    }
  })
}

export function checkCodeExist(uid = null, code, cancelToken = '') {
  let url = API_PATH+'/checkCode/'+code;
  if (uid !== null)  {
    url += '?uid='+uid;
  }
  return new Promise(async function(resolve, reject) {
    try {
      const results = await axios.get(url, { cancelToken });
      resolve(results);
    } catch (error) {
      reject(false);
    }
  })
}



export const searchByPage = (searchObject) => {
  return axios.post(API_PATH +  "searchByPage", searchObject);
};

export const filterByPage = (filter) => {
  let { category, nationaly, currencyPrice, currencyPayout } = filter;
  if (category !== null && (typeof category !== 'object' || !category.hasOwnProperty('id'))) filter.category = null;
  if (nationaly !== null && (typeof nationaly !== 'object' || !nationaly.hasOwnProperty('id'))) filter.nationaly = null;
  if (currencyPrice !== null && (typeof currencyPrice !== 'object' || !currencyPrice.hasOwnProperty('id'))) filter.currencyPrice = null;
  if (currencyPayout !== null && (typeof currencyPayout !== 'object' || !currencyPayout.hasOwnProperty('id'))) filter.currencyPayout = null;
  return axios.post(API_PATH +  "filterByPage", filter);
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



export const getAllCategory = () => {
  var url = API_PATH_Category + "/searchByPage";
  return axios.post(url, { pageIndex: 0, pageSize: 10000 });
};

export const getAllNationaly = () => {
  var url = API_PATH_Nationaly + "/searchByPage";
  return axios.post(url, { pageIndex: 0, pageSize: 10000 });
};

export const getAllCurrency = () => {
  var url = API_PATH_Currency + "/searchByPage";
  return axios.post(url, { pageIndex: 0, pageSize: 10000 });
};

export const getAllCampaignType = () => {
  var url = API_PATH_CampaignType + "/searchByPage";
  return axios.post(url, { pageIndex: 0, pageSize: 10000 });
};


export const uploadImage = (file, id) => {
  const url = ConstantList.API_ENPOINT + "/api/upload/image/product";
  let formData = new FormData();
  formData.append('file', file);
  formData.append('productID', id); 
  const config = {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  }
  return axios.post(url, formData, config)
}
