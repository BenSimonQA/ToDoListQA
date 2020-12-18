document
    .querySelector("form.updateTodo")
    .addEventListener("submit", function (stop) {
      stop.preventDefault();
      let formElements = document.querySelector("form.updateTodo").elements;
      console.log(formElements)
      let id=formElements["floatingID"].value;
      let name=formElements["floatingName"].value;
      let description =formElements["floatingDescription"].value;
  
      let data = {
        "id":id,
        "name":name,
        "description":description
    }
    console.log("Data to post",data)
    submit(data,id)
  
      // postData(noteTitle,noteBody)
    });

    function submit(data,id){
    fetch(`http://localhost:8080/todo/update/${id}/`, {
        method: "put",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(data),
      })
      .then(function(data){
          console.log("Success!!",data)
      }

      )
      alert(`Updated Todo ${id}!`)
    }

    document
    .querySelector("form.updateTask")
    .addEventListener("submit", function (stop) {
      stop.preventDefault();
      let formElements = document.querySelector("form.updateTask").elements;
      console.log(formElements)
      let id=formElements["floatingIDTask"].value;
      let name=formElements["floatingNameTask"].value;
      let difficulty =formElements["floatingDifficulty"].value;
  
      let data = {
        "id":id,
        "name":name,
        "difficulty":difficulty
    }
    console.log("Data to post",data)
    submitTask(data,id)
  
      // postData(noteTitle,noteBody)
    });

    function submitTask(data,id){
    fetch(`http://localhost:8080/task/update/${id}/`, {
        method: "put",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(data),
      })
      .then(function(data){
          console.log("Success!!",data)
      }

      )
      alert(`Updated Task ${id}!`)
    }