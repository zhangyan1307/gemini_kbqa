import request from '../utils/request'
import qs from 'qs';

export function getCategoryList(params) {
    return request({
        method:'get',
        url:'/api/customerKb/getCustomerCategories',
        params:{
            parentId:params
        }
    })
}
export function saveCategory(data) {
  return request({
    method: 'post',
    url:'/api/customerKb/saveCustomerCategory',
    data,
  })
}
export function getArticleByCategoryId(data) {
  return request({
    method:'get',
    url:'/api/customerKb/queryCustomerContentByCategoryId',
    params:{
        pageNum:data.pageNum,
        pageSize:data.pageSize,
        categoryId:data.categoryId
    }
})
}
export function saveCustomerArticle(data){
  return request({
    method: 'post',
    url: '/api/customerKb/file/upload',
    data,
    headers: {
      'Content-Type': 'multipart/form-data',
      // 'Access-Control-Allow-Origin': 'http://127.0.0.1:8080'
      //这里是为了解决跨域问题，但是博主并没有用这种方式解决。后面会给出解决方案
    }
  })
}