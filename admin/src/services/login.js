import instance from './index'

export const Login = params=> instance.post('/login', params)
export const GetMine = ()=> instance.get('/admin/mine')
