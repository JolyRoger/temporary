package content

import zio.{IO, Scope, UIO, ZIO, ZIOAppArgs, ZIOAppDefault}

object ZioMain extends ZIOAppDefault {

  val aaa = List(1,2,3)

  val res = Some(1).fold {
    println("Is empty")
  }(v => "" + v)

  val finalizer: UIO[Unit] = ZIO.succeed(println("Finalizing!"))
// finalizer: UIO[Unit] = Sync(
//   trace = "repl.MdocSession.MdocApp.finalizer(handling-resources.md:15)",
//   eval = <function0>
// )

  val finalized: IO[String, Unit] = ZIO.fail("Failed!").ensuring(finalizer)
// finalized: IO[String, Unit] = DynamicNoBox(
//   trace = "repl.MdocSession.MdocApp.finalized(handling-resources.md:19)",
//   update = 1L,
//   f = zio.ZIO$$$Lambda$18256/0x00007fc4fad1d430@793e85c2
// )


  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = finalized.map(_ => finalizer)
}
