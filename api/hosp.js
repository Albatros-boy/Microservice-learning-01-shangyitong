import request from "../utils/request";

const api_name = `/api/hosp/hospital`

export default {
  //查询医院列表
  getPageList(page,limit,searchObj) {
    return request({
      url: `${api_name}/findHospList/${page}/${limit}`,
      method:'get',
      params:searchObj
    })
  },
  getByHosname(hosname) {
    return request({
      url: `${api_name}/findByHosname/${hosname}`,
      method:'get'
    })
  }
}
