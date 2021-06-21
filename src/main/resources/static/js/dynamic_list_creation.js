export default {
    data() {
        return {
            list_items: [],
            list_name: '',
        };
    },
    template: `
    <div class="container">
    <h1 class="title">Add a new Shopping List!</h1>
    <p> List Name: <input class="inputbox" v-model="list_name" placeholder="list name"/></p>
    
        <h3>added Items: </h3>
        <tr colspan="2" class="table" v-for="item in list_items">
            <td>
                <h4>{{item}}</h4>
            </td>
            <td>
                <button class="button button_removing"  type="button" @click="list_items.pop(item)">Remove</button>
            </td>    
        </tr>
        <p> New Item: <input class="inputbox" v-model="itemName" placeholder="add a new Item" @keyup.enter="list_items.push(itemName)"/> <button class="button button_webpage"  type="button" @click="list_items.push(itemName)">Add item</button></p>
        
        <div class="buttons">
        <p><button class="button button_webpage"  type="button" @click="save_list">Submit</button> <button class="button button_webpage" type="button" @click="reset_inputfields">Reset</button></p>
        </div>
        </div>
    `,
    methods: {
        reset_inputfields() {
            this.list_items = [],
                this.itemName = '',
                // this.author = '',
                this.list_name = ''

        },
        save_list() {
            if (this.list_name !== '' && this.list_items !== []) {
                axios.post('/createlist', {
                    list_name: this.list_name,
                    list_items: this.list_items,
                })
                    .then((response) => {
                        this.reset_inputfields()
                        this.show_save_dialog()
                    }, (error) => {
                        Swal.fire('Something went wrong!')
                        console.log('Unable to save List!');
                    });
            } else {
                Swal.fire('The List is empty!')
            }
        },
        show_save_dialog() {
            Swal.fire({
                title: 'Shopping list added!',
                text: 'Shopping list was added successfully!',
                icon: 'success',
                showCancelButton: true,
                confirmButtonText: 'Add another list',
                cancelButtonText: 'Show all my lists'
            }).then((result) => {
                if (result.dismiss === Swal.DismissReason.cancel) {
                    window.location.href = "/alllists"
                }
            })
        }
    }
}





