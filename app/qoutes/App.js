import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';

export default class App extends React.Component {
  render() {
    return (
      <View style={styles.container}>
           <View style={{height: 25, backgroundColor: 'powderblue'}} />
           <View style={{height: 100, backgroundColor: 'powderblue'}} >
            <Text style={styles.styleTitle}>Quotes {"\n"}By React Natine</Text>
           </View>
           <View style={styles.styleTarget} >
             <Text style={styles.styleMsj}>Hola</Text>
           </View>
         <Button
            title="Learn More"
           color="#841584"
           accessibilityLabel="Learn more about this purple button"
         />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
     flex: 1,
     flexDirection: 'column',
     justifyContent: 'center',
     alignItems: 'stretch',
  },
  styleText:{
    color:'white',
    fontWeight: 'bold',
    justifyContent: 'center',
    fontSize: 30,
     marginTop: 10
  },
  styleMsj:{
      color:'white',
      fontSize: 20,
      padding: 10
  },
   styleTitle:{
          fontSize: 30,
          paddingLeft: 30,
      },
  styleTarget: {
       flex: 1,
       backgroundColor: '#FC8970',
       borderRadius: 12,
       margin:10,
       padding:20
    }
});
