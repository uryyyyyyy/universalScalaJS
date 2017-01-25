//@flow

import React, {Component} from 'react';
import Counter from './todo/Root';
import {Provider} from "react-redux";
import store from "./Store";

export class Index extends Component {
  render() {
    return (
      <Provider store={store}>
        <Counter />
      </Provider>
    );
  }
}
