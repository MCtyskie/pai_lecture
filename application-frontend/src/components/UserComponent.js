import React from 'react';
import UserService from '../services/UserService';

class UserComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            users: []
        }
    }

    componentDidMount() {
        UserService.getUsers().then((response) => {
            this.setState({ users: response.data })
        });
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Users list</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>User id</td>
                            <td>User name</td>
                            <td>User lastName</td>
                            <td>User email</td>
                            <td>User status</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user =>
                                    <tr key={user.id}>
                                        <td> {user.id}</td>
                                        <td> {user.name}</td>
                                        <td> {user.lastName}</td>
                                        <td> {user.email}</td>
                                        <td> {user.status}</td>
                                    </tr>
                            )
                        }
                    </tbody>

                </table>

            </div>
        )
    }
}

export default UserComponent