export class WidgetInfo {
  name: String;
  type: String;
  data: String| null;

  constructor(name:String, type: String, data:String | null) {
    this.name = name;
    this.type = type;
    this.data = data;
  }
}