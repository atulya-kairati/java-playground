"use client";

import {
  Flex,
  Box,
  FormControl,
  FormLabel,
  Input,
  Checkbox,
  Stack,
  Button,
  Heading,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
import LoginForm from "./components/LoginForm";
import { useAuth } from "./components/context/AuthContext";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

export default function LoginPage() {
  // if logged in then restrict the login page
  const { customer } = useAuth();
  const navigateTo = useNavigate();

  useEffect(() => {
    if (customer) {
      navigateTo("dashboard");
    }
  });

  return (
    <Flex
      minH={"100vh"}
      align={"center"}
      justify={"center"}
      bg={useColorModeValue("gray.50", "gray.800")}
    >
      <Stack spacing={8} mx={"auto"} maxW={"lg"} py={12} px={6}>
        <Stack align={"center"}>
          <Heading fontSize={"4xl"}>Log in to your account</Heading>
        </Stack>
        <Box
          rounded={"lg"}
          bg={useColorModeValue("white", "gray.700")}
          boxShadow={"lg"}
          p={8}
        >
          <LoginForm />
        </Box>
      </Stack>
    </Flex>
  );
}
