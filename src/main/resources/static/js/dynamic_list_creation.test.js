import {mount} from '@vue?test-utils'
import dynamic_list_creation from './dynamic_list_creation.js';

global.axios = require('axios');
global.axios = require('sweetalert2');

test('load template', () => {
    const wrapper = mount(dynamic_list_creation)

    expect(wrapper.text()).toContain('Add a new Shopping List!')
})