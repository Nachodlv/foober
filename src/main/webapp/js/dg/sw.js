function showNotification(){
    const title = 'Foober';
    const options = {
        body: 'You have an order!',
        icon: '../../images/FooberLogo.png',
        badge: '../../images/FooberLogo.png'
    };

    self.swRegistration.showNotification(title, options);
}