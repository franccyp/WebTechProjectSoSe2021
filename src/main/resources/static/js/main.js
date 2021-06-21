import dynamic_list_creation from './dynamic_list_creation.js';
import show_all_lists from './show_all_lists.js';
import contact_form from './contact_form.js';

const app = Vue.createApp({});

app.component('dynamic_list_creation', dynamic_list_creation);
app.component('show_all_lists', show_all_lists);
app.component('contact_form', contact_form);

app.mount('#app');
