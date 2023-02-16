import request from '../utils/request'

// const api_name = '/admin/hosp/hospitalSet'

export default {
  dictList(id) {
    return request({
      url: `/admin/cmn/dict/findChildData/${id}`,
      method: 'get'
    })
  },

}
