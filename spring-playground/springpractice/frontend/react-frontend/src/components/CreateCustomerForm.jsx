import {
  Alert,
  AlertIcon,
  FormLabel,
  Input,
  Select,
  Box,
  Button,
  Stack,
} from "@chakra-ui/react";
import { Formik, Form, useField } from "formik";
import * as Yup from "yup";
import { postCustomer } from "../services/client";
import {
  errorNotification,
  successNotification,
} from "../services/notification";

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

const MySelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <Box>
      <FormLabel htmlFor={props.id || props.name}>{label}</FormLabel>
      <Select {...field} {...props} />
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
const CreateCustomerForm = ({ fetchCustomers, onClose }) => {
  return (
    <>
      <Formik
        initialValues={{
          name: "",
          mail: "",
          age: 0,
          gender: "", // added for our select
          password: "",
        }}
        validationSchema={Yup.object({
          name: Yup.string()
            .max(20, "Must be 15 characters or less")
            .required("Required"),
          mail: Yup.string()
            .email("Invalid email address")
            .required("Required"),
          age: Yup.number()
            .min(18, "Age can't be less than 18")
            .max(100, "Age can't be greater than 100")
            .required("Required"),
          gender: Yup.string()
            .oneOf(["male", "female", "artificial", "other"], "Invalid Genda")
            .required("Required"),
          password: Yup.string().min(6).required(),
        })}
        onSubmit={(customer, { setSubmitting }) => {
          postCustomer(customer)
            .then((stat) => {
              console.log(stat);

              if (stat === 200) {
                fetchCustomers();
                onClose();
                successNotification(
                  "Customer created",
                  `${customer.name} has been saved!`
                );
              }
            })
            .catch((err) => {
              errorNotification("Unsuccessful", err.response.data.message);
            })
            .finally(() => setSubmitting(false));
        }}
      >
        {({ isValid, isSubmitting }) => (
          <Form>
            <Stack spacing={8}>
              <MyTextInput
                label="First Name"
                name="name"
                type="text"
                placeholder="Jane"
              />

              <MyTextInput
                label="Email Address"
                name="mail"
                type="mail"
                placeholder="jane@formik.com"
              />

              <MyTextInput label="Age" name="age" type="age" />

              <MySelect label="Gender" name="gender">
                <option value="">Select a Gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="artificial">Artificial</option>
                <option value="other">Other</option>
              </MySelect>

              <MyTextInput
                label="Password"
                name="password"
                type="password"
                placeholder="Enter a secure password"
              />

              <Button isDisabled={!isValid || isSubmitting} type="submit">
                Submit
              </Button>
            </Stack>
          </Form>
        )}
      </Formik>
    </>
  );
};

export default CreateCustomerForm;
