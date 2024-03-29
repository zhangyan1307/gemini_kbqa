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
export function deleteArticle(data) {
  return request({
    method: 'get',
    url: '/api/customerKb/deleteCustomerContent',
    params: {
      fileId: data.fileId,
      articleId: data.articleId
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

export function saveQuestion(data) {
  return request({
    method: 'post',
    url:'/api/questionAndAnswer/saveQuestion',
    data,
  })
}

export function deleteQuestion(data) {
  return request({
    method:'get',
    url:'/api/questionAndAnswer/deleteQuestion',
    params:{
        questionId:data,
    }
  })
}

export function getQuestionListPageByCategoryId(data) {
  return request({
    method:'get',
    url:'/api/questionAndAnswer/getQuestionList',
    params:{
        pageNum:data.pageNum,
        pageSize:data.pageSize,
        categoryId:data.categoryId
    }
  })
}

export function setRecomendFlag(data) {
  return request({
    method: 'post',
    url:'/api/questionAndAnswer/setRecommendFlag',
    data
  })
  
}