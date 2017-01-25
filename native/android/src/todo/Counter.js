// @flow

import React, {Component} from 'react';
import {
  StyleSheet,
  Text,
  View,
  Button,
  ScrollView,
  TextInput,
  Alert
} from 'react-native';
import {CounterState, ToDoState, IToDo} from "./Entities";
import {ToDoActionDispatcher} from "./Actions";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5FCFF',
  },
  cell: {
    borderColor: "#000000",
    borderWidth: 1,
    height: 50,
    flexDirection: 'row'
  },
  buttonView: {
    flexDirection: 'row'
  },
  normalText: {
    flex: 1,
    fontSize: 30,
  },
  completeText: {
    flex: 1,
    fontSize: 30,
    textDecorationLine: "line-through"
  },
  completeButton: {
    flex: 1,
  },
  deleteButton: {
    flex: 1,
  },
  addButton: {
    flex: 1,
  },
  saveButton: {
    flex: 1,
  },
});

type Props = {
  todoList: ToDoState;
  actions: ToDoActionDispatcher;
};

export class Counter extends Component<void, Props, void> {

  constructor(props) {
    super(props);
    this.state = { title: 'title' };
  }
  componentDidMount(){
    this.props.actions.fetchFromServer()
  }

  add() {
    this.props.actions.addToDo(this.state.title)
    this.setState({title: 'title'})
  }

  async save() {
    await this.props.actions.saveToServer()
    Alert.alert('save Done')
  }

  render() {
    const todoViews = this.props.todoList.map((todo:IToDo) => {
      const showTitleView = (isCompleted) => {
        if(isCompleted){
          return <Text style={styles.completeText}>
            {todo.title}
          </Text>
        }else{
          return <Text style={styles.normalText}>
            {todo.title}
          </Text>
        }
      }
      return (
        <View style={styles.cell} key={todo.id}>
          {showTitleView(todo.isCompleted)}
          <Button
            style={styles.completeButton}
            onPress={() => this.props.actions.complete(todo.id)}
            title="Complete"
            color="#841584"
          />
          <Button
            style={styles.deleteButton}
            onPress={() => this.props.actions.deleteToDo(todo.id)}
            title="Delete"
            color="#444444"
          />
        </View>
      )
    })

    console.log(this.props.todoList)
    return (
      <ScrollView style={styles.container}>
        {todoViews}
        <View style={styles.buttonView}>
          <TextInput
            style={{width: 200, height: 40}}
            onChangeText={(text: string) => this.setState({title: text})}
            editable = {true}
            maxLength = {40}
            value={this.state.title}
          />
          <Button
            style={styles.addButton}
            onPress={() => this.add()}
            title="Add"
            color="blue"
          />
          <Button
            style={styles.saveButton}
            onPress={() => this.save()}
            title="Save"
            color="green"
          />
        </View>
      </ScrollView>
    );
  }
}
