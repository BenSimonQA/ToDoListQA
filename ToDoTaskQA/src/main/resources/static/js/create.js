document
    .querySelector("form.createTodo")
    .addEventListener("submit", function (stop) {
      stop.preventDefault();
      let formElements = document.querySelector("form.createTodo").elements;
      console.log(formElements)
      let name=formElements["floatingName"].value;
      let description =formElements["floatingDescription"].value;
  
      let data = {
        "name":name,
        "description":description
    }
    console.log("Data to post",data)
    submit(data)
  
      // postData(noteTitle,noteBody)
    });

    function submit(data){
    fetch(`http://localhost:8080/todo/create`, {
        method: "post",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(data),
      })
      .then(function(data){
          console.log("Success!!",data)
      }

      )
      alert(`Added New Todo!`)
    }

    document
    .querySelector("form.createTask")
    .addEventListener("submit", function (stop) {
      stop.preventDefault();
      let formElements = document.querySelector("form.createTask").elements;
      console.log(formElements)
      let name=formElements["floatingNameT"].value;
      let difficulty =formElements["floatingDifficulty"].value;
      let todoID =formElements["floatingTodo"].value;
  
      let data = {
        "name":name,
        "difficulty":difficulty,
        "todo":{
          "id":todoID
        }
    }
    console.log("Data to post",data)
    submitTask(data)
  
      // postData(noteTitle,noteBody)
    });

    function submitTask(data){
    fetch(`http://localhost:8080/task/create`, {
        method: "post",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(data),
      })
      .then(function(data){
          console.log("Success!!",data)
      }

      )
      alert(`Added New Task!`)
    }