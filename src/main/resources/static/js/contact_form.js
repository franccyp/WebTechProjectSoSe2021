export default {

    data() {
        return {
            name: '',
            email: '',
            message: ''
        }
    },
    template: `    
    <section class="one">
    <div class="contact-section">
        <div class="contact-info">
            <div><i class="fas fa-map-marker-alt"></i>Genslerstraße 88, 10553 Berlin.</div>
            <div><i class="fas fa-envelope"></i>shopchop@email.com</div>
            <div><i class="fas fa-phone"></i>+49 30 290761833</div>
            <div><i class="fas fa-clock"></i>Mon - Fri 0900 to 1500</div>
        </div>
        <div class="contact-form">
            <h2>Contact Us</h2>
            <form class="contact" @submit.prevent="sendEmail">
                <input type="text" name="name" class="text-box" placeholder="Your Name" v-model="name" required>
                <input type="email" name="email" class="text-box" placeholder="Your Email" v-model="email" required>
                <textarea name="message" rows="5" placeholder="Your Message" v-model="message" required></textarea>
          
                <input type="submit" name="submit" class="send-btn" value="Send">
            </form>
        </div>
    </div>
    </section>
    `,
    methods: {
        sendEmail(e) {
            emailjs.sendForm('shopchop_contact_service', 'contact_form', e.target, 'user_cT3E059w6mjwI1ubNkYGQ', {
                name: this.name,
                email: this.email,
                message: this.message
            })
                .then((response) => {
                    this.show_sent_dialog()
                    console.log('SUCCESS!');
                }, (error) => {
                    Swal.fire('Something went wrong!')
                    console.log('FAILED...', error);
                });

            // Reset form field
            this.name = ''
            this.email = ''
            this.message = ''
        },
        show_sent_dialog() {
            Swal.fire({
                title: 'Email has been sent!',
                text: 'Check your inbox to view our reply! Thank you.',
                icon: 'success'
            })
        }
    }
}