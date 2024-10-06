import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getMatchmakingConferenceOne = (params) => {
    return getRequest('/matchmakingConference/getOne', params)
}
export const getMatchmakingConferenceList = (params) => {
    return getRequest('/matchmakingConference/getByPage', params)
}
export const getMatchmakingConferenceCount = (params) => {
    return getRequest('/matchmakingConference/count', params)
}
export const addMatchmakingConference = (params) => {
    return postRequest('/matchmakingConference/insert', params)
}
export const editMatchmakingConference = (params) => {
    return postRequest('/matchmakingConference/update', params)
}
export const addOrEditMatchmakingConference = (params) => {
    return postRequest('/matchmakingConference/insertOrUpdate', params)
}
export const deleteMatchmakingConference = (params) => {
    return postRequest('/matchmakingConference/delByIds', params)
}