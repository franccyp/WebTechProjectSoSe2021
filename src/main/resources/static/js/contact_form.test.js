import {mount} from '@vue/test-utils'
import contactForm from './contact_form.js';

global.axios = require('sweetalert2');

test('load contact form template', () => {
    const wrapper = mount(contactForm);
    expect(wrapper.text()).toContain('Contact Us');
})



