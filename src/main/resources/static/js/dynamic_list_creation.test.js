import {mount} from '@vue/test-utils'
import dynamicListCreation from './dynamic_list_creation';

global.axios = require('axios');
global.axios = require('sweetalert2');

test('load template', () => {
    const wrapper = mount(dynamicListCreation);
    expect(wrapper.text()).toContain('Add a new Shopping List!');
})