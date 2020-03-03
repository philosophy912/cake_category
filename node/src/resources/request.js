import { axios } from 'axios';
import 'mock.js';
import { basicGetAll } from '@/resources/url.js';

export function getBasicAll(){
    return axios.post(basicGetAll);
}