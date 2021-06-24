import {mount} from '@vue/test-utils'
import dynamicListCreation from './dynamic_list_creation';

global.axios = require('axios');
global.axios = require('sweetalert2');

test('load template', () => {
    const wrapper = mount(dynamicListCreation);
    expect(wrapper.text()).toContain('Add a new Shopping List!');
})

test('add an item', async () => {
    const wrapper = mount(dynamicListCreation);
    const itemName = wrapper.find("#itemName");
    await itemName.setValue('new item');
    const addItemButton = wrapper.find('#addItemButton')
    await addItemButton.trigger('click');
    expect(wrapper.vm.$data.list_items[0]).toBe('new item');

})