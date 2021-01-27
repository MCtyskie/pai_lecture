import React, { Component } from 'react';
import User from "./User"

class App extends Component {

  state = {
    data: [],
  }

  componentDidMount() {
    fetch('http://localhost:8081/users')
      .then(response => response.json())
      .then(data => {
        console.log(data);
        this.setState({ data })
      }
      );
  }


  render() {
    return (
      <div>
        {this.state.data.map(user => <User info={user}/>)}
      </div>
    );
  }
}

export default App;
