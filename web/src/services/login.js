import instance from './index'

export const Login = params=> instance.post('/login', params)
export const Register = params => instance.post('/register', params)
export const Check = params => instance.get('/check/' + params)
