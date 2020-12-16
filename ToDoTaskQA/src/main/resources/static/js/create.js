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