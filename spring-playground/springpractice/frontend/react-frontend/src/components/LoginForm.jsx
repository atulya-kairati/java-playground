import {
  Alert,
  AlertIcon,
  FormLabel,
  Input,
  Select,
  Box,
  Button,
  Stack,
  Checkbox,
} from "@chakra-ui/react";
import { Formik, Form, useField, Field } from "formik";
import * as Yup from "yup";
import { performLogin } from "../services/client";
import {
  errorNotification,
  successNotification,
} from "../services/notification";
import { useAuth } from "./context/AuthContext";
import { useNavigate } from "react-router-dom";

const MyTextInput = ({ label, ...props }) => {
  // useField() returns [formik.getFieldProps(), formik.getFieldMeta()]
  // which we can spread on <input>. We can use field meta to show an error
  // message if the field is invalid and it has been touched (i.e. visited)
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Input className="text-input" {...field} {...props} />
      {meta.touched && meta.error ? (
        <Alert className="error" status="error">
          <AlertIcon />
          {meta.error}
        </Alert>
      ) : null}
    </Box>
  );
};

// And now we can use these
const LoginForm = ({ onLogin }) => {
  const { login } = useAuth();

  const navigateTo = useNavigate();

  return (
    <>
      <Formik
        initialValues={{
          mail: "",
          password: "",
          remember: true,
        }}
        validationSchema={Yup.object({
          mail: Yup.string()
            .email("Invalid email address")
            .required("Required"),
          password: Yup.string().min(6).required("Required"),
          remember: Yup.boolean(),
        })}
        onSubmit={(authData, { setSubmitting }) => {
          console.log(authData);

          setSubmitting(true);
          login(authData.mail, authData.password)
            .then((res) => {
              // Navigate to dashboard
              navigateTo("dashboard");
            })
            .catch((err) => {
              console.log(err);
              errorNotification("Unsuccessful", err.response.data.msg);
            })
            .finally(() => {
              setSubmitting(false);
            });
        }}
      >
        {({ isValid, isSubmitting, dirty }) => (
          <Form>
            <Stack spacing={8}>
              <MyTextInput
                label="Email Address"
                name="mail"
                type="mail"
                placeholder="jane@bcbc.com"
              />

              <MyTextInput label="Password" name="password" type="password" />

              <label>
                <Field name="remember" type="checkbox" />
                {" Remember me"}
              </label>

              <Button
                isDisabled={!(isValid && dirty) || isSubmitting}
                type="submit"
                colorScheme="teal"
              >
                Login
              </Button>
            </Stack>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default LoginForm;
