from flask import Flask , render_template, request
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

app = Flask(__name__)
app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///todo.db"
db = SQLAlchemy(app)

class Todo(db.Model):

    id = db.Column(db.Integer, primary_key=True)

    title = db.Column(db.String(80), nullable=False)

    desc = db.Column(db.Text, nullable=False)

    date_created = db.Column(db.DateTime, default=datetime.utcnow) # 데이터 작성 시간

    date_modified = db.Column(db.DateTime, default=datetime.utcnow) # 데이터 수정 시간

    is_done = db.Column(db.Boolean, default=False)


    def __repr__(self):   #클래스 객체를 문자열로 표현

        return f"{self.id} - {self.title}"
    
@app.route("/", methods = ["GET", "POST"])

def index():
    if request.method == "POST":
        title = request.form['title']
        desc = request.form['desc']
        todo = Todo(title=title, desc=desc)
        db.session.add(todo)
        db.session.commit()
    todos = Todo.query.all()
    return render_template("index.html", todos =todos)


@app.route("/delete/<int:id>", methods = ["GET", "POST"])
def delete(id):
    todo = Todo.query.get_or_404(id)
    db.session.delete(todo)
    db.session.commit()
    return redirect("/")

if __name__ == "__main__":

    with app.app_context():
        db.create_all()
    app.run(debug=True, port =8000)