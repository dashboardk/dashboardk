export class WidgetInfo {
  name: String;
  type: String;
  repoName: String | null;
  branchName: String| null;

  constructor(name:String, type: String, repoName:String | null, branchName:String | null) {
    this.name = name;
    this.type = type;
    this.repoName = repoName;
    this.branchName = branchName;
  }
}