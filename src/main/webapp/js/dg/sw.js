function showNotification(){
    const title = 'Foober';
    const options = {
        body: 'You have an order!',
        icon: '../../images/FooberLogo.png',
        badge: '../../images/FooberLogo.png'
    };

    if(self.swRegistration) self.swRegistration.showNotification(title, options);
}