import React, { Component } from 'react';


class User extends Component {

    render() {
        return (
            <div>
                <p>{this.props.info.userId}</p>
                <p>{this.props.info.name}</p>
                <p>{this.props.info.lastName}</p>
                <p>{this.props.info.email}</p>
                <p>{this.props.info.status}</p>
                <p>-------------------------</p>
            </div>
            
        );
    }
}

export default User;