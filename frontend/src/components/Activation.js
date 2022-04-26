import React, { useState, useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";
import { activation } from "../actions/auth";
const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};
const validEmail = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const Activation = () => {
  const form = useRef();
  const checkBtn = useRef();
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [successful, setSuccessful] = useState(false);
  const { message } = useSelector(state => state.message);
  const dispatch = useDispatch();
  const onChangeEmail = (e) => {
    const email = e.target.value;
    setEmail(email);
  };
  const handleActivation = (e) => {
    e.preventDefault();
    setSuccessful(false);
    form.current.validateAll();
    if (checkBtn.current.context._errors.length === 0) {
      dispatch(activation(email))
        .then(() => {
          setSuccessful(true);
        })
        .catch(() => {
          setSuccessful(false);
        });
    }
  };
  return (
    <div className="col-md-12">
      <div className="card card-container">
        <div>Activate Account</div>
        <Form onSubmit={handleActivation} ref={form}>
          {!successful && (
            <div>
              <div className="form-group">
                <label htmlFor="email">Email</label>
                <Input
                  type="text"
                  className="form-control"
                  name="email"
                  value={email}
                  onChange={onChangeEmail}
                  validations={[required, validEmail]}
                />
              </div>
              <div className="form-group">
                <button className="btn btn-primary btn-block">Sign Up</button>
              </div>
            </div>
          )}
          {successful && (
            <div className="form-group">
              <div className={successful ? "alert alert-success" : "alert alert-danger"} role="alert">
                <div>Account activated.</div>
                <div>Please check your email inbox</div>
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
        </Form>
      </div>
    </div>
  );
};
export default Activation;