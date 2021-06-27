export default {

    data() {
        return {
            edit: false,
            items: [],
            shopping_lists: [],
            search: ''
        };
    },
    template: `
      <h1 class="shopping_list_title">Here are all your shopping lists created</h1>
      <input class="inputbox searchbar" type="text" v-model="search" placeholder="search list by Title"/>
      <div class="shopping_list_body">
      <ul class="shopping_list" v-for="shopping_list in filtered_lists">
        <li> <text class="slist_small_title">{{shopping_list.list_name}}</text> 
          <button title="Delete List" class="slist_small_button" @click="delete_list(shopping_list.list_id)"> ✖ </button> 
        </li>
        <li>
          <div>
            <ul v-for="list_item in shopping_list.listItems">
              <li class="slist_items">{{list_item.item_name}}  
                <button title="Delete Item" class="slist_small_button" @click="delete_item(list_item)"> ✖ </button> 
                <button title="Check Item" class="slist_small_button" @click="check_item(list_item)"> ✔ </button> 
              </li>
            </ul>
            <ul>
              <li class="slist_items">
                <button title="Add Item" class="slist_small_button" @click="add_item(shopping_list.list_id)">➕</button>
              </li>
            </ul>
          </div>
        </li>
      </ul>
      <ul class="shopping_list">
        <li><button class="slist_small_button" type="button" @click="redirect_to_create_list">Create a new List!</button></li>
        <ul>
          <li class="slist_items">...</li>
          <li class="slist_items">...</li>
          <li class="slist_items">...</li>
        </ul>
      </ul>
      </div>
    `,
    computed: {
        filtered_lists() {
            return this.shopping_lists.filter(shopping_list => {
                return shopping_list.list_name.toLowerCase().indexOf(this.search.toLowerCase()) > -1
            })
        }
    },
    methods: {
        redirect_to_create_list() {
            window.location.href = "/createlist"
        },
        delete_list(list_id) {
            let delete_url = '/shoppinglists/remove/' + list_id
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post(delete_url)
                        .then((response) => {
                            this.load_lists();
                        }, (error) => {
                            Swal.fire('Unable to delete list!');
                            console.log('Unable to delete list!');
                        });
                    Swal.fire(
                        'Deleted!',
                        'Your list has been deleted.',
                        'success'
                    )
                }
            })
        },
        async add_item(list_id) {
            let item_name;
            let shopList = this.shopping_lists.find(x => x.list_id === list_id);
            const {value: text} = await Swal.fire({
                title: 'Add more items to your list!',
                input: 'text',
                inputPlaceholder: 'Add your item here',
                inputAttributes: {
                    'aria-label': 'Add your item here'
                },
                showCancelButton: true
            })

            if (text) {
                Swal.fire(`${text} is now added into your list!`)
            }

            item_name = text;
            shopList.listItems.push(item_name);
            let url = '/shoppinglists/updatelist/' + list_id + '/' + item_name
            axios.post(url, {
                itemName: item_name
            }).then((updatedList) => {
                this.load_lists();
            }, (error) => {
                Swal.fire('Unable to add item!');
                console.log('Unable to add item!');
            });
        },
        delete_item(item) {
            let delete_url = '/item/remove/' + item.item_id
            axios.post(delete_url)
                .then((response) => {
                    this.load_lists();
                }, (error) => {
                    Swal.fire('Unable to delete item!');
                    console.log('Unable to delete item!');
                });
        },
        check_item(item) {
            let checked_itemName = item.item_name
                .split('')
                .map(char => char + '\u0336')
                .join('')
            console.log(checked_itemName)
            let url = '/item/updatename/' + item.item_id + '/' + checked_itemName
            axios.put(url, {
                itemName: checked_itemName
            }).then((updatedItem) => {
                this.load_lists();
            }, (error) => {
                Swal.fire('Unable to check item out of the list!');
                console.log('Unable to check item out of the list!');
            });
        },
        load_lists() {
            fetch('/shoppinglists').then((response) => {
                if (response.ok) {
                    return response.json();
                }
            }).then((data) => {
                this.shopping_lists = data;
            }, (error) => {
                console.log('Unable to load lists');
            });
        }

    },
    mounted: function () {
        this.load_lists();
    }
}