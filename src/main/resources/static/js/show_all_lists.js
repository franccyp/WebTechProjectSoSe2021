const app = Vue.createApp({})

app.component('show_all_lists', {

    data() {
        return {};
    },
    template: `
    
    `,
    methods: {

        deleteList() {
            axios.post('/createlist', {
                list_name: this.list_name,
                list_items: this.list_items,
            })
                .then((response) => {
                    this.reset_inputfields()
                }, (error) => {
                    console.log('could not save Product!');
                });
        }
    }
})

app.mount('#show_all_lists')