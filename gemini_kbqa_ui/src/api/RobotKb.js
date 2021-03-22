import request from '../utils/request'
export function getCustomerContentByCategoryId(params) {
  return request({
      method:'get',
      url:'/robot/robotKb/queryCustomerContentByCategoryId',
      params:{
        keyword:params
      }
  })
}

export function getRecommendQuestions() {
  return request({
      method:'get',
      url:'/robot/robotKb/queryCustomerRecommendQuestion',
  })
}