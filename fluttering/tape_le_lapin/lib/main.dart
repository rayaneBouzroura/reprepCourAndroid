import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {



  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _indexLapin = Random().nextInt(6);

  int _flops = 0;

  int _pafs = 0;




  @override
  Widget build(BuildContext context) {
    print(this._indexLapin.toString());
    var b1 = MaterialButton(
      onPressed: () {
        print("bouton 0");
        if (this._indexLapin == 0){
          this._pafs++;
          _indexLapin = Random().nextInt(6);
        }
        else{
          this._flops++;
        }
        setState(() {});
      },
      child: Text(this._indexLapin == 0 ? "lapin" : "taupe"),
    );
    var b2 = MaterialButton(
      onPressed: () {
        print("bouton 1");
        if (this._indexLapin == 1){
          this._pafs++;
          _indexLapin = Random().nextInt(6);
        }
        else{
          this._flops++;
        }
        setState(() {});
      },
      child: Text(this._indexLapin == 1 ? "lapin" : "taupe"),
    );
    var b3 = MaterialButton(
      onPressed: () {
        print("bouton 2");
        if (this._indexLapin == 2){
          this._pafs++;
          _indexLapin = Random().nextInt(6);
        }
        else{
          this._flops++;
        }
        setState(() {});
      },
      child: Text(this._indexLapin == 2 ? "lapin" : "taupe"),
    );
    var b4 = MaterialButton(
      onPressed: () {
        print("bouton 3");
        if (this._indexLapin == 3){
          this._pafs++;
          _indexLapin = Random().nextInt(6);
        }
        else{
          this._flops++;
        }
        setState(() {});
      },
      child: Text(this._indexLapin == 3 ? "lapin" : "taupe"),
    );
    var b5 = MaterialButton(
      onPressed: () {
        print("bouton 4");
        if (this._indexLapin == 4){
          this._pafs++;
          _indexLapin = Random().nextInt(6);
        }
        else{
          this._flops++;
        }
        setState(() {});
      },
      child: Text(this._indexLapin == 4 ? "lapin" : "taupe"),
    );
    var b6 = MaterialButton(
      onPressed: () {
        print("bouton 5");
        if (this._indexLapin == 5){
          this._pafs++;
          _indexLapin = Random().nextInt(6);
        }
        else{
          this._flops++;
        }
        setState(() {});
      },
      child: Text(this._indexLapin == 5 ? "lapin" : "taupe"),
    );

    return Scaffold(
      appBar: AppBar(

        backgroundColor: Theme.of(context).colorScheme.inversePrimary,

        title: Text("hit ze rabbit 🐇🐇"),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
              Row(
               mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Text(
                    "pafs :${this._pafs}",
                    style: TextStyle(color: Colors.green , fontSize: 20),
                    ),
                Text(
                    "flops :${this._flops}",
                  style: TextStyle(color: Colors.red , fontSize: 20),
                ),
              ],
              ),
            Text(
              "tape le lapin",//bold large text style
              style: TextStyle(fontSize: 30, fontWeight: FontWeight.w900),

            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                b1,
                b2,
                b3,
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,

              children: [
                b4,
                b5,
                b6,
              ],
            ),
          ],
        ),
      ),
    );
  }
}
