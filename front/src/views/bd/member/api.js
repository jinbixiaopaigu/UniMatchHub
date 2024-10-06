import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getMemberOne = (params) => {
    return getRequest('/member/getOne', params)
}
export const getMemberList = (params) => {
    return getRequest('/member/getByPage', params)
}
export const getMemberCount = (params) => {
    return getRequest('/member/count', params)
}
export const addMember = (params) => {
    return postRequest('/member/insert', params)
}
export const editMember = (params) => {
    return postRequest('/member/update', params)
}
export const addOrEditMember = (params) => {
    return postRequest('/member/insertOrUpdate', params)
}
export const deleteMember = (params) => {
    return postRequest('/member/delByIds', params)
}