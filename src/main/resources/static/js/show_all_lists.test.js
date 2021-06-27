import {mount} from '@vue/test-utils'
import allList from './show_all_lists.js';

global.axios = require('sweetalert2');
global.fetch = require("node-fetch");

test('load show all lists template',  () => {
    const wrapper = mount(allList);
    expect(wrapper.text()).toContain('Here are all your shopping lists created');
})



